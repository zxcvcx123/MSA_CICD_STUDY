package org.msa.item.exception;

import org.msa.item.dto.ResponseDTO;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

//Spring에서 전역적으로 Exception에 대한 처리를 진행, 응답형태를 그냥 ResponseEntity로 하는경우 @ControllerAdvice로 사용하면됨
@RestControllerAdvice	
public class ApiExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exception(HttpServletRequest requset, Exception e) throws Exception {
		ResponseDTO.ResponseDTOBuilder response = ResponseDTO.builder();
		
		response.code("500").message(e.getMessage());
		return ResponseEntity.ok(response.build());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) throws JSONException {
		BindingResult bindingResult = ex.getBindingResult();
		StringBuilder builder = new StringBuilder();
		
		for(FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append("[");
			builder.append(fieldError.getField());
			builder.append("](은)는 ");
			builder.append(fieldError.getDefaultMessage());
			builder.append(" 입력된 값: [");
			builder.append(fieldError.getRejectedValue());
			builder.append("]");
		}
		
		ResponseDTO.ResponseDTOBuilder response = ResponseDTO.builder();
		
		response.code("500").message(builder.toString());
		return ResponseEntity.ok(response.build());
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> ApiException(HttpServletRequest request, ApiException e) throws Exception {
		ResponseDTO.ResponseDTOBuilder responseBuilder = ResponseDTO.builder();
        responseBuilder.code("501").message(e.getMessage());
        return ResponseEntity.ok(responseBuilder.build());
	}
	
}
