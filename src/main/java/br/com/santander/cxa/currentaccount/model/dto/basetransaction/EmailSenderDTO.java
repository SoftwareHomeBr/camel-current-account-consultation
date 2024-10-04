package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class EmailSenderDTO {

	private String emailSend;
	private String[] email;
}
