package com.github.uniliva.restutils.exception;

import com.github.uniliva.commonsutils.exception.BaseException;

public class NaoEncontradoException extends BaseException {
	private static final long serialVersionUID = 1L;

	public NaoEncontradoException() {
		this.getErro().setMensagem("Não encontrado!");
	}

	public NaoEncontradoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NaoEncontradoException(final String message) {
		super(message);
	}

	public NaoEncontradoException(final Object detalhe) {
		super("", detalhe);
	}

	public NaoEncontradoException(final Throwable cause) {
		super("", cause);
	}

	public NaoEncontradoException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

}
