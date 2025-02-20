package org.msa.authentication.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class
ResponseDTO {

	private String code;
	private String message;
	private String token;
}
