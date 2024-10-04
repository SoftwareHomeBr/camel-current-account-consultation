package br.com.santander.cxa.currentaccount.processor;

import java.util.LinkedHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.google.gson.Gson;

import br.com.santander.cxa.currentaccount.model.dto.CurrentAccountBalanceResponseDTO;
import br.com.santander.cxa.util.StringNumberFormatter;

public class FormatResponseFieldsAndPrepareForEncryptionProcessor implements Processor {

	@Override
	public void process(Exchange e) throws Exception {
	    Gson gson = new Gson(); 
	    String json = gson.toJson(e.getMessage().getBody(), LinkedHashMap.class);
	    var currentAccountBalanceResponseDTO = gson.fromJson(json, CurrentAccountBalanceResponseDTO.class);
		StringNumberFormatter.formatObject(currentAccountBalanceResponseDTO);
	    e.getMessage().setBody(currentAccountBalanceResponseDTO);

		String encryptedObject = (String) e.getProperty("X-EncryptedObject");
		e.getMessage().setHeader("X-EncryptedObject", encryptedObject);  
	}

}
