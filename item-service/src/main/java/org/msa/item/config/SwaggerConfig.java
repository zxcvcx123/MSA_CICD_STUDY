package org.msa.item.config;

import java.beans.JavaBean;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
 
	 @Bean
	    public GroupedOpenApi itemGroupedOpenApi() {
	        return GroupedOpenApi
	                .builder()
	                .group("v1") // group 설정 (API들을 그룹화시켜 그룹에 속한 API들만 확인할 수 있도록 도와줌)
	                .pathsToMatch("v1/item/**") // group에 포함될 API endpoint 경로
	                .addOpenApiCustomizer(openApi ->
	                        openApi.setInfo(new Info()
	                                        .title("물품 처리요청 API") // API 제목
	                                        .description("물품 처리요청 API") // API 설명
	                                        .version("v1") // API 버전
	                                        )
	                )
	                .build();
	    }
 }
