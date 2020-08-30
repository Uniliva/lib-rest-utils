package com.github.uniliva.restutils.resource;

import static com.github.uniliva.restutils.enums.HttpEnum.HTTP_200;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.uniliva.restutils.dto.DadosResponse;
import com.github.uniliva.restutils.enums.HttpEnum;

public interface BaseResource {

	/**
	 * <p>
	 * Este método será acessado por todas as classes de resource para retornar
	 * sucesso, incluindo o parâmetro "response" na generalização "dados" de
	 * DadosResponse
	 *
	 * @param <T>      - Classe que define o tipo de retorno
	 * @param httpEnum - Enum que defino o codigo de retorno
	 * @param response - Dados do response
	 * @return Um response entity
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarResponse(final HttpEnum httpEnum,
			final T response) {
		return ResponseEntity.status(httpEnum.getStatus()).body(new DadosResponse<>(httpEnum, response));
	}

	/**
	 * Retorna dados com status 200
	 * 
	 * @param <T>      - Classe que define o tipo de retorno
	 * @param response - Dados do response
	 * @return Um response entity com status 200
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSucesso(final T response) {
		return response != null ? retornarResponse(HTTP_200, response) : retornarSemConteudo();
	}

	/**
	 * Retorna uma lista de dados com status 200
	 * 
	 * @param <T>      - Classe que define o tipo de retorno
	 * @param response - Dados do response
	 * @return Um response entity com uma lista de dados com status 200
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
	 * @param <T>         - Classe que define o tipo de retorno
	 * @param responseOpt - Optional que de um tipo de dados
	 * @return Um response entity com dados se tiver, ou retorno sem conteudo com
	 *         status 200
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSucesso(final Optional<T> responseOpt) {
		return responseOpt.isPresent() ? retornarResponse(HTTP_200, responseOpt.get()) : retornarSemConteudo();
	}

	/**
	 * Retorna dados response entity com dados e status de criado
	 * 
	 * @param <T>      - Classe que define o tipo de retorno
	 * @param response - Dados do response
	 * @return response entity com dados e status de criado
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriado(final T response) {
		return retornarResponse(HttpEnum.HTTP_201, response);
	}

	/**
	 * Retorna com status 201 e com a url do recurso no header
	 * 
	 * @param <T>   - Classe que define o tipo de retorno
	 * @param id    - Id do recurso criado 
	 * @return   response entity com status criado e a url do recurso no header
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriadoHeader(final Long id) {
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	/**
	 * Retorna com status 201 porem sem dados
	 * @param <T>  - Classe que define o tipo de retorno
	 * @return   response entity com status criado, porem sem dados
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarCriado() {
		return retornarResponse(HttpEnum.HTTP_201, null);
	}

	/**
	 * Retonar sem contudo e com status 204
	 * @param <T>  - Classe que define o tipo de retorno
	 * @return response entity com status com status 204, sem conteudo
	 */
	default <T extends Object> ResponseEntity<DadosResponse<T>> retornarSemConteudo() {
		return retornarResponse(HttpEnum.HTTP_204, null);
	}

}
