package br.com.santander.cxa.currentaccount.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataRequest;
import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.LogResponseDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.TapeRecordRequestDTO;
import br.com.santander.cxa.currentaccount.route.integration.LogRoute;

public class CashierTapeProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogRoute.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		BaseRequestDTO request = (BaseRequestDTO) exchange.getProperty("requestData");
		var logResponseDTO = (LogResponseDTO)exchange.getProperty("openLogData"); 
		var saldo = exchange.getProperty("saldo");
		
		if (request != null)
		{
			LOGGER.info(request.toString());
			var tapeRecordRequest = new TapeRecordRequestDTO();
			
			tapeRecordRequest.setAccountNumber(request.getAccount().getAccountNumber());
			tapeRecordRequest.setBankBranchCode(request.getAccount().getBranchCode());
			tapeRecordRequest.setBankCode(request.getAccount().getBankCode());
			tapeRecordRequest.setOperatorCode(request.getContext().getOperatorCode());
			tapeRecordRequest.setTerminal(request.getContext().getTerminalCode());
			tapeRecordRequest.setTransactionCode(request.getTransactionCode());
			tapeRecordRequest.setTransactionNsu(logResponseDTO.getTransactionNsu());
			tapeRecordRequest.setValue((String) saldo);
			tapeRecordRequest.setCustomerName((String)exchange.getProperty("nomeCliente"));
			
			exchange.getMessage().setBody(tapeRecordRequest);
			exchange.getMessage().setHeader("loginAd", request.getOperatorUserCode());
		}
	}

}
