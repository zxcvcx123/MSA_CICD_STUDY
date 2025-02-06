package org.msa.item.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Getter
@Builder
public class ResponseDTO {

	private String code;
	private String message;
}
