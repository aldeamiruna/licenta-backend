package com.inventory.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.ItemTypeService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/types")
public class ItemTypeController {

	@Autowired
	private ItemTypeService service;

	@GetMapping("/")
	public Response getAllTypes() {
		return new Response(service.getAll());
	}

	@RequestMapping(value = "/insertItemType", produces = "application/json", method = RequestMethod.PUT)
	public Response insertItemType(@RequestBody HashMap<String,String> itemType) {
		return new Response(service.insertItemType(itemType));
	}
	

	@GetMapping("/itemTypes")
	public Response getItemTypes() {
		return new Response(service.getItemTypes());
	}

	@GetMapping("/distinctObjectTypes")
	public Response getDistinctObjectTypes() {
		return new Response(service.getDistinctObjectTypes());
	}

}
