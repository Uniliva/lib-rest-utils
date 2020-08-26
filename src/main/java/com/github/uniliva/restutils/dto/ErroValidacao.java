package com.github.uniliva.restutils.dto;

import java.util.ArrayList;
import java.util.List;

import com.github.uniliva.commonsutils.dto.BaseDto;
import com.github.uniliva.commonsutils.dto.Erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErroValidacao extends BaseDto {
	private static final long serialVersionUID = -6487111911459784100L;

	private List<Erro> lsErrosValidacao = new ArrayList<>();

	public void addErro(String nome, String mensagem) {
		lsErrosValidacao.add(new Erro(nome, mensagem));
	}

}
