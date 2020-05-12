package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventory.service.UserStatusService;
import com.inventory.utils.Response;

@CrossOrigin
@RestController
@RequestMapping("/userStates")

public class UserStatusController {

	@Autowired
	private UserStatusService service;

	@GetMapping("/distinctStates")
	public Response getDistinctStates() {
		return new Response(service.getDistinctUserStatus());
	}

	@GetMapping("/")
	public Response getAll() {
		return new Response(service.getAll());
	}
}
