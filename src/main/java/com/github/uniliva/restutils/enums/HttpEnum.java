package com.github.uniliva.restutils.enums;

import org.springframework.http.HttpStatus;

public enum HttpEnum {
	HTTP_200(HttpStatus.OK, "Sucesso"), HTTP_201(HttpStatus.CREATED, "Criado"),
	HTTP_204(HttpStatus.OK, "Nenhum conteúdo"),

	HTTP_304(HttpStatus.NOT_MODIFIED, "Não modificado"),

	HTTP_400(HttpStatus.BAD_REQUEST, "Requisição inválida"), HTTP_401(HttpStatus.UNAUTHORIZED, "Não autorizado"),
	HTTP_404(HttpStatus.NOT_FOUND, "Não encontrado"),
	HTTP_500(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor (Internal Server Error)");

	private HttpStatus status;
	private String mensagem;

	private HttpEnum(final HttpStatus status, final String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public boolean is2xxSuccessful() {
		return status.is2xxSuccessful();
	}

	public boolean is4xxClientError() {
		return status.is4xxClientError();
	}

	public boolean is5xxServerError() {
		return status.is5xxServerError();
	}

}
