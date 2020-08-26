package com.github.uniliva.restutils.dto;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import com.github.uniliva.commonsutils.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseWebDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	private transient HttpHeaders header;
	private transient HttpServletRequest request;

}
