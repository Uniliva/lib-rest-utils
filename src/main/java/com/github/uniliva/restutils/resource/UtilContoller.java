package com.github.uniliva.restutils.resource;

import static com.github.uniliva.restutils.enums.HttpEnum.HTTP_200;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.uniliva.restutils.dto.DadosResponse;
import com.github.uniliva.restutils.enums.HttpEnum;

public interface UtilContoller {

	/**
	 * <p>
	 * Este método será acessado por todas as classes de resource para retornar
	 * sucesso, incluindo o parâmetro "response" na generalização "dados" de
	 * DadosResponse
	 *
	 * @param response {@link T}
	 * @return {@link ResponseEntity<DadosResponse<T>>}
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarResponse(final HttpEnum httpEnum,
			final T response) {
		return ResponseEntity.status(httpEnum.getStatus()).body(new DadosResponse<>(httpEnum, response));
	}

	/*
	 * HTTP 200
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSucesso(final T response) {
		return response != null ? retornarResponse(HTTP_200, response) : retornarSemConteudo();
	}

	/*
	 * HTTP 200
	 */
	default <T extends Object> ResponseEntity<DadosResponse<List<T>>> retornarSucesso(final List<T> response) {
		return !response.isEmpty() ? retornarResponse(HTTP_200, response) : retornarSemConteudo();
	}

	/**
	 * <p>
	 * Através de um {@link Optional} verifica se há um retorno para a chamada, em
	 * caso positivo, retorna http 200 com o recurso retornado, caso contrário,
	 * retorna http 204 com uma mensagem padrão de recurso não encontrado.
	 *
	 * @param responseOpt
	 * @return
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSucesso(final Optional<T> responseOpt) {
		return responseOpt.isPresent() ? retornarResponse(HTTP_200, responseOpt.get()) : retornarSemConteudo();
	}

	/**
	 * Este método deve ser utilizado apenas pelos serviços de health.
	 *
	 * @return
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSucesso() {
		return retornarResponse(HttpEnum.HTTP_200, null);
	}

	/*
	 * HTTP 201
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriado(final T response) {
		return retornarResponse(HttpEnum.HTTP_201, response);
	}

	/*
	 * HTTP 201
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriadoHeader(final Long id) {
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriado() {
		return retornarResponse(HttpEnum.HTTP_201, null);
	}

	/*
	 * HTTP 204
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSemConteudo() {
		return retornarResponse(HttpEnum.HTTP_204, null);
	}

	/*
	 * HTTP 304
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarNaoAlterado() {
		return retornarResponse(HttpEnum.HTTP_304, null);
	}

}
