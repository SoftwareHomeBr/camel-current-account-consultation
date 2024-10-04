package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class PrinterControlDTO {

	private String printable;
	private String numberOfPrintCopies;
	private String numberOfAuthentications;
}
