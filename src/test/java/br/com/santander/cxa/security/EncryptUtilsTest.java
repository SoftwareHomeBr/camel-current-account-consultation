package br.com.santander.cxa.security;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

class EncryptUtilsTest {

	@Test
	void testEncrypt() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";
		String bancoEncrypted = EncryptUtils.encrypt("0033", encryptedObject, true);
		System.out.println("bancoEncrypted: " + bancoEncrypted);
		assertNotNull(bancoEncrypted);
	}

	@Test
	void testDecrypt() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";
		String bancoDecrypted = EncryptUtils.decrypt("8cJWBTKJqx0BajJYy+PsjA==", encryptedObject, false);
		assertNotNull(bancoDecrypted);
	}

	@Test
	void testDecryptJson() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";

		String jsonString = "{\"info\":\"8cJWBTKJqx0BajJYy+PsjA==\"}";
		JSONObject json = new JSONObject(jsonString);
		
		JSONObject jsonDecrypted = EncryptUtils.decryptJsonObject(json, encryptedObject, true, false);
		assertNotNull(jsonDecrypted);
	}

//	public static void main(String [] args) throws Exception
//	{
//		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";
//		String data = "BANANA";
//		String result = EncryptUtils.encrypt(data, encryptedObject, false);
//		System.out.println(result);
//	}
	
	@Test
	void testEncryptJson() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";

		String jsonString = "{\"info\":\"BANANA\"}";
		Gson gson = new Gson();
		LinkedTreeMap j = gson.fromJson(jsonString, LinkedTreeMap.class);
		
		LinkedTreeMap jsonEncrypted = EncryptUtils.encryptForRequest(j, encryptedObject);
		assertNotNull(jsonEncrypted);
	}

	@Test
	void testDecryptFromRequest() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";

		String jsonString = "{\"info\":\"OXAjZSDUx8FuQD7zuI4kGA==\"}";
		Gson gson = new Gson();
		LinkedTreeMap j = gson.fromJson(jsonString, LinkedTreeMap.class);
		LinkedTreeMap jsonDecrypted = EncryptUtils.decryptFromRequest(j, encryptedObject);
		
		
		assertNotNull(jsonDecrypted );
	}
	
	@Test
	void testDecryptFromRequestFailure() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";

		String jsonString = "{\"info\":\"OXAjZSDUx8FuQD7zuI4kGA==\", \"array\":[{\"attr1\":\"OXAjZSDUx8FuQD7zuI4kGA==\"}]}";
		Gson gson = new Gson();
		LinkedTreeMap j = gson.fromJson(jsonString, LinkedTreeMap.class);
		
		LinkedTreeMap jsonDecrypted = null;
		
		try
		{
			jsonDecrypted = EncryptUtils.decryptFromRequest(j, encryptedObject);
		}
		catch (Exception e)
		{
		}
		
		assertNotNull(j);
	}
	
	@Test
	void testEncryptJson2() throws Exception {
		String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3KwAAACb7w8rMz87MysjJw8/JyMnNy8nJzc7Mzc3Iy8nLz8rDyc7DzMPLz8J4";

		String jsonString = "{\r\n" + 
				"  \"altairMessage\": {\r\n" + 
				"    \"altairError\": [\r\n" + 
				"      {\r\n" + 
				"        \"errorCode\": \"string\",\r\n" + 
				"        \"errorMessage\": \"string\"\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"altairWarning\": [\r\n" + 
				"      {\r\n" + 
				"        \"warningCode\": \"string\",\r\n" + 
				"        \"warningMessage\": \"string\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  \"bgm402\": [\r\n" + 
				"    {\r\n" + 
				"      \"codigoCtaCliente\": \"string\",\r\n" + 
				"      \"divisa\": \"string\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"bgm4020\": [\r\n" + 
				"    {\r\n" + 
				"      \"divisaDeLaPcuenta\": \"string\",\r\n" + 
				"      \"divisaDeLaAcuenta\": \"string\",\r\n" + 
				"      \"ppalDisponible\": \"string\",\r\n" + 
				"      \"ppalContable\": \"string\",\r\n" + 
				"      \"ppalSdoRetenc\": \"string\",\r\n" + 
				"      \"ppalSdoRetPrec\": \"string\",\r\n" + 
				"      \"ppalSdoRetHoy\": \"string\",\r\n" + 
				"      \"ppalSdoRetenido\": \"string\",\r\n" + 
				"      \"ppalSdoCanje\": \"string\",\r\n" + 
				"      \"ppalSdoRemesas\": \"string\",\r\n" + 
				"      \"ppalLimite\": \"string\",\r\n" + 
				"      \"ppalExcedido\": \"string\",\r\n" + 
				"      \"saldoIndeterminado\": \"string\",\r\n" + 
				"      \"valorFundo\": \"string\",\r\n" + 
				"      \"saldoCpmf\": \"string\",\r\n" + 
				"      \"disponivelTedcip\": \"string\",\r\n" + 
				"      \"retenidoTed\": \"string\",\r\n" + 
				"      \"retenidoCip\": \"string\",\r\n" + 
				"      \"importeCpmf\": \"string\",\r\n" + 
				"      \"saldoMismoTitular\": \"string\",\r\n" + 
				"      \"dispoConjun\": \"string\",\r\n" + 
				"      \"impAcumIof\": \"string\",\r\n" + 
				"      \"jurosAcumulados\": \"string\",\r\n" + 
				"      \"inteDevacre\": \"string\",\r\n" + 
				"      \"inteDevdeud\": \"string\",\r\n" + 
				"      \"inteDevexce\": \"string\",\r\n" + 
				"      \"inteDevinad\": \"string\",\r\n" + 
				"      \"inteDevmora\": \"string\",\r\n" + 
				"      \"indSubcta\": \"string\",\r\n" + 
				"      \"fechaUltope\": \"string\",\r\n" + 
				"      \"fechaUltopi\": \"string\",\r\n" + 
				"      \"fechaUltliq\": \"string\",\r\n" + 
				"      \"fechaProliq\": \"string\",\r\n" + 
				"      \"fechaVcto\": \"string\",\r\n" + 
				"      \"fechaExcedi\": \"string\",\r\n" + 
				"      \"indSaldoAgilizado\": \"string\",\r\n" + 
				"      \"indExtAtm\": \"string\",\r\n" + 
				"      \"contChqDisponibles\": \"string\",\r\n" + 
				"      \"saldoAnterior\": \"string\",\r\n" + 
				"      \"saldoPoupanca\": \"string\",\r\n" + 
				"      \"saldoPendiente\": \"string\",\r\n" + 
				"      \"sdoMedioDeudor\": \"string\",\r\n" + 
				"      \"sdoMedioMesActual\": \"string\",\r\n" + 
				"      \"saldoMes2\": \"string\",\r\n" + 
				"      \"saldoMes3\": \"string\",\r\n" + 
				"      \"numDiasExce\": \"string\",\r\n" + 
				"      \"numDiasDeud\": \"string\",\r\n" + 
				"      \"descriDivisa\": \"string\",\r\n" + 
				"      \"nomTitular\": \"string\",\r\n" + 
				"      \"ape1Titular\": \"string\",\r\n" + 
				"      \"ape2Titular\": \"string\",\r\n" + 
				"      \"sucursal\": \"string\",\r\n" + 
				"      \"descriSucursal\": \"string\",\r\n" + 
				"      \"producto\": \"string\",\r\n" + 
				"      \"descriProducto\": \"string\",\r\n" + 
				"      \"subproducto\": \"string\",\r\n" + 
				"      \"descriSubproducto\": \"string\",\r\n" + 
				"      \"disponibleAsociada\": \"string\",\r\n" + 
				"      \"contableAsociada\": \"string\",\r\n" + 
				"      \"asoSdoRetenc\": \"string\",\r\n" + 
				"      \"asoSdoRetPrec\": \"string\",\r\n" + 
				"      \"asoSdoRetHoy\": \"string\",\r\n" + 
				"      \"asoSdoRetenido\": \"string\",\r\n" + 
				"      \"asoSdoCanje\": \"string\",\r\n" + 
				"      \"asoSdoRemesas\": \"string\",\r\n" + 
				"      \"asoLimite\": \"string\",\r\n" + 
				"      \"asoExcedido\": \"string\",\r\n" + 
				"      \"pcuenta\": \"string\",\r\n" + 
				"      \"acuenta\": \"string\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"bgm4021\": [\r\n" + 
				"    {\r\n" + 
				"      \"resarcimientoCpmf\": \"string\",\r\n" + 
				"      \"cpmfIncidido\": \"string\",\r\n" + 
				"      \"dispoMismoTitular\": \"string\",\r\n" + 
				"      \"saldoDepositoPrazo\": \"string\",\r\n" + 
				"      \"valorPoupmax\": \"string\",\r\n" + 
				"      \"saldoBloqueiado\": \"string\",\r\n" + 
				"      \"sldBloqueiadoHoje\": \"string\",\r\n" + 
				"      \"sldBloqRemessas\": \"string\",\r\n" + 
				"      \"sldBloqmaisDias\": \"string\",\r\n" + 
				"      \"situacaoDaConta\": \"string\",\r\n" + 
				"      \"saldoBloqJudicial\": \"string\",\r\n" + 
				"      \"saldoBloqueioDia\": \"string\",\r\n" + 
				"      \"saldoCcCci\": \"string\",\r\n" + 
				"      \"provisaoDeEncargos\": \"string\",\r\n" + 
				"      \"saldoDisponCcCci\": \"string\",\r\n" + 
				"      \"limiteChequeInvest\": \"string\",\r\n" + 
				"      \"iofAdicionalAcumul\": \"string\",\r\n" + 
				"      \"saldoDispTotal\": \"string\",\r\n" + 
				"      \"saldoDisponivel\": \"string\",\r\n" + 
				"      \"sldRetJudPmax\": \"string\",\r\n" + 
				"      \"saldoBloqTotal\": \"string\",\r\n" + 
				"      \"descricaoLimite1\": \"string\",\r\n" + 
				"      \"descricaoLimite2\": \"string\",\r\n" + 
				"      \"lancProcCredito\": \"string\",\r\n" + 
				"      \"lancProcDebito\": \"string\",\r\n" + 
				"      \"sldTotCcCmax\": \"string\",\r\n" + 
				"      \"sldBloqJudiCci\": \"string\",\r\n" + 
				"      \"sldContCcCmax\": \"string\",\r\n" + 
				"      \"sldBlq24hCcCmax\": \"string\",\r\n" + 
				"      \"sldBlq48hCcCmax\": \"string\",\r\n" + 
				"      \"sldBlqIndCcCmax\": \"string\",\r\n" + 
				"      \"fechaUltPeriodific\": \"string\",\r\n" + 
				"      \"segPresSdoFechado\": \"string\",\r\n" + 
				"      \"segPresSdoAberto\": \"string\",\r\n" + 
				"      \"indRegraAnterior\": \"string\",\r\n" + 
				"      \"sdoContabAnterior\": \"string\",\r\n" + 
				"      \"sdoBloqueadoAnteri\": \"string\",\r\n" + 
				"      \"desbloq1DiaAnteri\": \"string\",\r\n" + 
				"      \"desbloq2DiasAnter\": \"string\",\r\n" + 
				"      \"desbl2DiasAnter\": \"string\",\r\n" + 
				"      \"sdoBloqJudicAnter\": \"string\",\r\n" + 
				"      \"cpmfSSaqueAnter\": \"string\",\r\n" + 
				"      \"cpmfProvisionAnter\": \"string\",\r\n" + 
				"      \"cpmfSSaldoAnter\": \"string\",\r\n" + 
				"      \"adicCpmfSSaldoAn\": \"string\",\r\n" + 
				"      \"sdtransfMmaTitAn\": \"string\",\r\n" + 
				"      \"sddisponTotAnter\": \"string\",\r\n" + 
				"      \"indicadorRegraNova\": \"string\",\r\n" + 
				"      \"sdoContabRegraNov\": \"string\",\r\n" + 
				"      \"sdoBloqueadoNova\": \"string\",\r\n" + 
				"      \"desbloq1DiaNova\": \"string\",\r\n" + 
				"      \"desbloq2DiasNova\": \"string\",\r\n" + 
				"      \"desbl2DiasNova\": \"string\",\r\n" + 
				"      \"sdoBloqJudicNova\": \"string\",\r\n" + 
				"      \"cpmfSSaqueNova\": \"string\",\r\n" + 
				"      \"cpmfProvisionNova\": \"string\",\r\n" + 
				"      \"cpmfSSaldoNova\": \"string\",\r\n" + 
				"      \"adicCpmfSSdoNova\": \"string\",\r\n" + 
				"      \"sdtransfMmaTitNv\": \"string\",\r\n" + 
				"      \"sddisponTotNova\": \"string\",\r\n" + 
				"      \"indCtPoupancaNova\": \"string\",\r\n" + 
				"      \"indicador2\": \"string\",\r\n" + 
				"      \"valorSaldo1\": \"string\",\r\n" + 
				"      \"valorSaldo2\": \"string\",\r\n" + 
				"      \"valorSaldo3\": \"string\",\r\n" + 
				"      \"valorSaldo4\": \"string\",\r\n" + 
				"      \"valorSaldo5\": \"string\",\r\n" + 
				"      \"valorSaldo6\": \"string\",\r\n" + 
				"      \"valorSaldo7\": \"string\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"";
		Gson gson = new Gson();
		LinkedTreeMap j = gson.fromJson(jsonString, LinkedTreeMap.class);
		
		LinkedTreeMap jsonEncrypted = EncryptUtils.encryptForRequest(j, encryptedObject);
		assertNotNull(jsonEncrypted);
	}
}
