package br.com.santander.cxa.currentaccount.model.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TapeRecordResponseDTO {

	private Boolean sucesso;
	private ErrorDTO error;
}
