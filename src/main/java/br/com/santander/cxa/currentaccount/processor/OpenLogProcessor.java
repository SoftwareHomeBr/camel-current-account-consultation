package br.com.santander.cxa.currentaccount.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.ContextDataDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.LogRequestDTO;
import br.com.santander.cxa.currentaccount.route.integration.LogRoute;

public class OpenLogProcessor implements Processor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogRoute.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		BaseRequestDTO request = (BaseRequestDTO) exchange.getIn().getBody();
		
		if (request != null)
		{
			LOGGER.info(request.toString());
			
			LogRequestDTO logRequest = new LogRequestDTO();
			ContextDataDTO context = new ContextDataDTO();
			
			context.setBank(request.getContext().getBank());
			context.setBranch(request.getContext().getBranch());
			context.setOperatorCode(request.getContext().getOperatorCode());
			context.setPremise(request.getContext().getPremise());
			context.setSupervisor(request.getContext().getSupervisor());
			context.setTerminalCode(request.getContext().getTerminalCode());
			
			logRequest.setContext(context);
			logRequest.setBancoCliente(Long.valueOf(request.getAccount().getBankCode()));
			logRequest.setAgenciaCliente(Long.valueOf(request.getAccount().getBranchCode()));
			logRequest.setContaCliente(Long.valueOf(request.getAccount().getAccountNumber()));
			logRequest.setNumeroCartao(request.getAccount().getCardNumber());
			logRequest.setTipoConsulta("1");
			logRequest.setTerminal(request.getTerminal());
			
			exchange.getMessage().setBody(logRequest);
			exchange.getMessage().setHeader("loginAd", request.getOperatorUserCode());
		}
	}

}