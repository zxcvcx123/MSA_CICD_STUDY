package org.msa.item.controller;

import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="v1/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
	
	private final ItemService itemService;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@RequestBody ItemDTO itemDTO){
		ResponseDTO.ResponseDTOBuilder response = ResponseDTO.builder();
		
		itemService.insertItem(itemDTO);
		
		log.debug("request add item id = {}", itemDTO.getId());
		
		response.code("200").message("성공");
		return ResponseEntity.ok(response.build());
	
	}
	
}
