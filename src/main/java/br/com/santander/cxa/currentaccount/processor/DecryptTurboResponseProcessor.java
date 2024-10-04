package br.com.santander.cxa.currentaccount.processor;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;
import br.com.santander.cxa.security.EncryptUtils;
import br.com.santander.cxa.util.POM;

public class DecryptTurboResponseProcessor implements Processor {
	
	@Override
	public void process(Exchange e) throws Exception {
		Map map = (Map)e.getMessage().getBody();
		if (map.get("bgm4020")==null)
			throw new Exception("Invalid turbo response:" + map.toString());
		String encryptedObject = (String) e.getProperty("X-EncryptedObject");
		Gson gson = new Gson();
		String objectJson = gson.toJson(e.getMessage().getBody());
				
		JSONObject json = new JSONObject(objectJson);
		json = EncryptUtils.decryptJsonObject(json, encryptedObject, true, true);
		//System.out.println("Payload recebido do turbo decifrado: " + json);
		e.getMessage().setBody(new ByteArrayInputStream(json.toString().getBytes()));
		BigData.add(e, BigDataEnum.VERSAO_APLIC, POM.getVersion());
	}
}
