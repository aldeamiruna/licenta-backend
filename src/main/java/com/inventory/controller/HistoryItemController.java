package com.inventory.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Item;
import com.inventory.service.HistoryItemService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/history")
public class HistoryItemController {

	@Autowired
	private HistoryItemService service;
	
	@GetMapping("/")
	public Response getHistory() {
		return new Response(service.getAll());
	}
	
	@RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)//deprecated , is done directly from addItem stored procedure
	public Response addRecord(@RequestBody Item item) {
		Response response = new Response();
		response.setMessage(service.addItemRecord(item));
		return response;
	}
	
	@RequestMapping(value = "/currentItem", produces = "application/json", method = RequestMethod.POST)
	public Response getItemHistory(@RequestBody HashMap<String,Integer> reqBody) {
		return new Response(service.getItemHistory(reqBody));
	}
	
}
