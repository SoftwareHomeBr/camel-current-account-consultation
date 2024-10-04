package br.com.santander.cxa.security;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.altec.bsbr.app.dl.ecc.DLECC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.com.santander.dlb.dlbcryptoloader.DLBCryptoLoader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EncryptUtils {
	
	private static final ObjectMapper mapper = new ObjectMapper();

	private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtils.class);

	public static <T> T encryptForRequest(T obj, String encryptedObject) throws Exception {
		DLECC dlEcc = (DLECC) DLBCryptoLoader.deserialize(encryptedObject);
		dlEcc.setClient(true);

		Gson gson = new Gson();
		String objectJson = gson.toJson(obj);

		JSONObject json = new JSONObject(objectJson);

		handleJSONObject(json, dlEcc, obj.getClass(), false);

		obj = gson.fromJson(json.toString(), getSpecificTypeListFromObject(obj));

		return (T) mapper.convertValue(obj, obj.getClass());
	}
	

	public static String encrypt (String data, String encryptedObject, boolean client) throws Exception
	{
		DLECC dlEcc = (DLECC) DLBCryptoLoader.deserialize(encryptedObject);
		if (client)
		{
			dlEcc.setClient(client);
		}
		return dlEcc.encrypt(data);
	}

	public static String decrypt (String data, String encryptedObject, boolean client) throws Exception
	{
		DLECC dlEcc = (DLECC) DLBCryptoLoader.deserialize(encryptedObject);
		if (client)
		{
			dlEcc.setClient(client);
		}
		return dlEcc.decrypt(data);
	}
	
	public static <T> T decryptFromRequest(T obj, String encryptedObject) {

		T response = null;
		
		try {
			DLECC dlEcc = (DLECC) DLBCryptoLoader.deserialize(encryptedObject);
			dlEcc.setClient(true);

			Gson gson = new Gson();
			String objectJson = gson.toJson(obj);
			JSONObject jsonObject = new JSONObject(objectJson);

			decryptJsonObject(jsonObject, dlEcc, obj.getClass(), true);
			response = (T) gson.fromJson(jsonObject.toString(), obj.getClass());

		} catch (IOException | ClassNotFoundException e) {
			LOGGER.info("Ocorreu um erro ao descriptrografar o json.");
			throw new RuntimeException(e);
		}

		return response;
	}

	private static void handleJSONObject(JSONObject jsonObject, DLECC dlCrypto, Class<?> clazz, boolean isResponse) {
		if (jsonObject != null && dlCrypto != null) {
			jsonObject.keys().forEachRemaining(key -> {
				Object value = jsonObject.get(key);

				try {
					if (value instanceof String && !(key.toLowerCase().contains("farol")
							|| key.toLowerCase().contains("rules") || key.toLowerCase().contains("groupitemtype"))) {
						if (!isResponse) {
							String v = (String) value;
							jsonObject.put(key, dlCrypto.encrypt(v));
						} else {
							String v = (String) value;
							jsonObject.put(key, dlCrypto.decrypt(v));
						}
					} else {
						handleValue(value, dlCrypto, clazz, isResponse);
					}
				} catch (Exception e) {
					LOGGER.error("Não foi possível criptografar o json: " + jsonObject);
					throw new RuntimeException(e);
				}
			});
		}
	}

	
	
	public static JSONObject decryptJsonObject(JSONObject jsonObject, String encryptedObject, boolean isResponse, boolean client) {
		if (encryptedObject != null)
		{
			DLECC dlEcc = null;
			try {
				dlEcc = (DLECC) DLBCryptoLoader.deserialize(encryptedObject);
				if (client)
				{
					dlEcc.setClient(true);
				}
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			decryptJsonObject(jsonObject, dlEcc, null, isResponse);
		}
		return jsonObject;
	}

	

	public static void decryptJsonObject(JSONObject jsonObject, DLECC dlCrypto, Class<?> clazz, boolean isResponse) {

		if (jsonObject != null && dlCrypto != null) {
			jsonObject.keys().forEachRemaining(key -> {
				Object value = jsonObject.get(key);
				try {
					if (value instanceof String) {

						String v = (String) value;
						jsonObject.put(key, dlCrypto.decrypt(v));

					} else {
						handleValue(value, dlCrypto, clazz, isResponse);
					}
				} catch (Exception e) {
					LOGGER.info("Não foi possível descriptografar o json: {}", jsonObject);
					throw new RuntimeException(e);
				}
			});
		}
	}

	private static JSONArray handleJSONArray(JSONArray jsonArray, DLECC dlCrypto, Class<?> clazz, boolean isResponse) {
		for (int i = 0; i < jsonArray.length(); i++) {
			Object value = jsonArray.get(i);

			if (value instanceof String) {
				try {
					if (isResponse) {
						String decryptedValue = dlCrypto.decrypt((String) value);
						jsonArray.put(i, decryptedValue);
					} else {
						String encryptedValue = dlCrypto.encrypt((String) value);
						jsonArray.put(i, encryptedValue);
					}
					continue;
				} catch (Exception e) {
				}
			}

			handleValue(value, dlCrypto, clazz, isResponse);
		}

		return jsonArray;
	}

	private static void handleValue(Object value, DLECC dlCrypto, Class<?> clazz, boolean isResponse) {
		if (value instanceof JSONObject) {
			handleJSONObject((JSONObject) value, dlCrypto, clazz, isResponse);
		} else if (value instanceof JSONArray) {
			handleJSONArray((JSONArray) value, dlCrypto, clazz, isResponse);
		}
	}

	private static Type getSpecificTypeListFromObject(Object o) {
		return new TypeToken<Object>() {
		}.getType();
	}

}
