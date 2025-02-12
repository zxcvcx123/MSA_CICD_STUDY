package org.msa.item.controller;

import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.exception.ApiException;
import org.msa.item.service.ItemService;
import org.msa.item.valid.ItemTypeValid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/v1/item")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {
	
	private final ItemService itemService;

	@Operation(summary = "물품등록 요청", description = "물품 등록을 진행할 수 있다.", tags = { "addItem" })
	@ApiResponses({
	        @ApiResponse(responseCode = "200", description = "SUCCESS"),
	        @ApiResponse(responseCode = "501", description = "API EXCEPTION")
	})
	@RequestMapping(value="/add/{itemType}", method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO, @ItemTypeValid @PathVariable(value = "itemType") String itemType) throws Exception{
		ResponseDTO.ResponseDTOBuilder response = ResponseDTO.builder();
		/*
		log.debug("path.variable itemType = {}", itemType);
		
		boolean hasItemType = false;
		ItemType [] itemTypeList = ItemType.values();
		
		for(ItemType i : itemTypeList) {
			hasItemType = i.hasItemCd(itemType);
			if(hasItemType) break;
		}
		
		if(!hasItemType) {
			response.code("500").message("invalid itemTpye . [" + itemType + "]");
			return ResponseEntity.ok(response.build());
		} else {
			itemDTO.setItemType(itemType);
		}
		*/
		/*
		try {
			Integer.parseInt("test");
		}catch(Exception e) {
			throw new ApiException("test에러");
		}
		*/
		
		itemDTO.setItemType(itemType);
		
		itemService.insertItem(itemDTO);
		
		log.debug("request add item id = {}", itemDTO.getId());
		
		response.code("200").message("성공");
		return ResponseEntity.ok(response.build());
	
	}
	
}
