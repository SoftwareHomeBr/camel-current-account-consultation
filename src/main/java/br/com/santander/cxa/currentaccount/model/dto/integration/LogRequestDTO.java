package br.com.santander.cxa.currentaccount.model.dto.integration;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogRequestDTO {

	private ContextDataDTO context;
	private Long bancoCliente;
	private Long agenciaCliente;
	private Long contaCliente;
	private String numeroCartao;
	private String tipoConsulta;
	private Boolean isSucesso;
	private String terminal;
	private String nsu;
}
