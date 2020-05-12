package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.service.StateService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/states")

public class StateController {

	@Autowired
	private StateService service;

	@GetMapping("/itemStates")
	public Response getItemTypes() {
		return new Response(service.getItemStates());
	}

	@GetMapping("/distinctObjectStates")
	public Response getDistinctObjectStates() {
		return new Response(service.getDistinctObjectStates());
	}
	
	@GetMapping("/")
	public Response getAll() {
		return new Response(service.getAll());
	}

}
