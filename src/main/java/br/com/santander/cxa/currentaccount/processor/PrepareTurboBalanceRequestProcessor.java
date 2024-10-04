package br.com.santander.cxa.currentaccount.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.altec.bsbr.app.dl.util.StringUtils;

import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.security.EncryptUtils;

public class PrepareTurboBalanceRequestProcessor implements Processor {

	@Override
	public void process(Exchange e) throws Exception {
		BaseRequestDTO request = (BaseRequestDTO)e.getProperty("requestData");
		String encryptedObject = (String) e.getProperty("X-EncryptedObject");
		String bank = StringUtils.fillLeft(request.getAccount().getBankCode(), 4, '0');
		String branch = StringUtils.fillLeft(request.getAccount().getBranchCode(), 4, '0');
		String account = StringUtils.fillLeft(request.getAccount().getAccountNumber(), 12, '0');
		String codigoCtaCliente = bank + branch + account;
		String codigoCtaClienteEncrypted = EncryptUtils.encrypt(codigoCtaCliente, encryptedObject, true);
		String divisaEncrypted = EncryptUtils.encrypt("BRL", encryptedObject, true);
		e.getIn().setHeader("codigoCtaCliente", codigoCtaClienteEncrypted);
		e.getIn().setHeader("divisa", divisaEncrypted);
	}

}
