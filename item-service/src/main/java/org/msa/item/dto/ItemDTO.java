package org.msa.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemDTO {
	
	@NotBlank(message = "ID는 필수 입력 값 입니다.")
	@Size(max = 10, message = "ID는 최대 10자리까지 작성가능합니다.")
	private String id;
	
	@Size(max = 20, message = "이름은 20자까지 작성가능합니다.")
	private String name;
	
	@Size(max = 200, message = "설명은 최대 200자까지 작성가능합니다.")
	private String description;
	
	@Positive /* 양수만 입력 가능한 어노테이션 */
	private long count;
	
	private String regDts;
	
	private String updDts;


}
