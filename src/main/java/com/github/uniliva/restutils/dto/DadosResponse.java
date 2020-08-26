package com.github.uniliva.restutils.dto;

import java.time.LocalDateTime;

import com.github.uniliva.restutils.enums.HttpEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DadosResponse<T> {

	private LocalDateTime timestamp;
	private Integer nrStatus;
	private T dados;
	private String nmSiglaProjeto;
	private String txMensagem;

	public DadosResponse(HttpEnum status, T dados) {
		this.timestamp = LocalDateTime.now();
		this.nrStatus = status.getStatus().value();
		this.txMensagem = status.getMensagem();
		this.nmSiglaProjeto = System.getenv("NOME_PROJETO");
		this.dados = dados;
	}

}
