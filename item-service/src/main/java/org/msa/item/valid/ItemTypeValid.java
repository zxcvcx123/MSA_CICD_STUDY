package org.msa.item.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.msa.item.dto.constant.ItemType;
import org.msa.item.valid.ItemTypeValid.ItemTypeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Constraint(validatedBy = ItemTypeValidator.class) // 유효성 체크 할 때 어떤 클래스를 통해 유효성 처리할건지
@Documented	// Java Doc로 작성될 때 설명을 포함
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) // 어느 대상을 기준으로 처리할 지 정하는거
@Retention(RetentionPolicy.RUNTIME) // 어느 시점까지 어노테이션 메모리를 갖고 갈것이냐
public @interface ItemTypeValid {
	
	String message() default "허용되지 않은 물품 유형입니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	class ItemTypeValidator implements ConstraintValidator<ItemTypeValid, String> {
		@Override
		public boolean isValid(String cd, ConstraintValidatorContext context) {
			
			boolean hasItemType = false;
			ItemType[] itemTypeList = ItemType.values();
			
			for(ItemType i : itemTypeList) {
				hasItemType = i.hasItemCd(cd);
				if(hasItemType) break;
			}
			
			return hasItemType;
		}
	}

}
