package br.com.santander.cxa.currentaccount.model.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RewriteLogResponseDTO {

	private Long nsu;		//Descricao: -> NSU da consultiva - somente retorno
	private Long retorno;	//sem descricao
}
