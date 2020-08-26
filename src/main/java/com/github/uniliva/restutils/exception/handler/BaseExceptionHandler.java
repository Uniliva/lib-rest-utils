package com.github.uniliva.restutils.exception.handler;

import static com.github.uniliva.restutils.enums.HttpEnum.HTTP_400;
import static com.github.uniliva.restutils.enums.HttpEnum.HTTP_500;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.uniliva.commonsutils.dto.Erro;
import com.github.uniliva.commonsutils.dto.ErroValidacao;
import com.github.uniliva.commonsutils.exception.ErroDeNegocioException;
import com.github.uniliva.restutils.dto.DadosResponse;
import com.github.uniliva.restutils.exception.BaseException;
import com.github.uniliva.restutils.exception.NaoEncontradoException;
import com.github.uniliva.restutils.resource.UtilContoller;

@ControllerAdvice
public class BaseExceptionHandler implements UtilContoller {

	@ExceptionHandler({ NaoEncontradoException.class })
	ResponseEntity<DadosResponse<Erro>> notFound(NaoEncontradoException e, HttpServletRequest request) {
		return retornarResponse(HTTP_400, e.getErro());
	}

	@ExceptionHandler({ ErroDeNegocioException.class })
	ResponseEntity<DadosResponse<Erro>> notFound(BaseException e, HttpServletRequest request) {
		return retornarResponse(HTTP_500, e.getErro());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<DadosResponse<ErroValidacao>> validationError(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		final ErroValidacao erroValidacao = new ErroValidacao();

		e.getBindingResult().getFieldErrors().stream()
				.forEach(erro -> erroValidacao.addErro(erro.getField(), erro.getDefaultMessage()));
		return retornarResponse(HTTP_400, erroValidacao);
	}

}
