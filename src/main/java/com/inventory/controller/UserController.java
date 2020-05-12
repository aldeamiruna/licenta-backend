package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.User;
import com.inventory.service.UserService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public Response getAllUsers() {
		return new Response(service.getAll());
	}

	@GetMapping("/get/companyId")
	public Response getUserByCompanyId(String targetId) {
		return new Response(service.getUserByCompanyId(targetId));
	}

	@RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
	public Response addUser(@RequestBody User user) {
		Response response = new Response();
		response.setMessage(service.addUser(user));
		return response;
	}

	@DeleteMapping("/delete")
	public Response deleteUser(int targetId) {
		Response response = new Response();
		response.setMessage(service.deleteUser(targetId));
		return response;
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
	public Response updateUser(@RequestBody User user) {
		Response response = new Response();
		response.setMessage(service.updateUser(user));
		return response;
	}

	@RequestMapping(value = "/update/stateByType", produces = "application/json", method = RequestMethod.POST)
	public Response updateUserStatus(@RequestParam String userId, @RequestParam String stateType) {
		return new Response(service.updateStateByType(userId, stateType));
	}

	@GetMapping("/usersCompanyNames")
	public Response getUsersByCompanyName() {
		return new Response(service.getUsersByCompanyName());
	}

	@GetMapping("/companyIds")
	public Response getCompanyIds() {
		return new Response(service.getCompanyIds());
	}

	@GetMapping("/lastNameList")
	public Response getLastNameList() {
		return new Response(service.getLastNameList());
	}

	@GetMapping("/firstNameList")
	public Response getFirstNameList() {
		return new Response(service.getFirstName());
	}

	@GetMapping("/emailList")
	public Response getEmaiList() {
		return new Response(service.getEmailList());
	}

	@RequestMapping(value = "/filterUsersByStatus", produces = "application/json", method = RequestMethod.POST)
	public Response getItemByCustomCrieria(@RequestBody User bodyUser) {

		return new Response(service.filterUsersByStatus(bodyUser));
	}
	
	@GetMapping("/all")
	public Response getUserTable() {
		return new Response(service.getUserTable());
	}
	
	/* Test */
	
	@GetMapping("/laptops")
	public Response getLaptops(@RequestParam int id) {
		return new Response(service.getLaptops(id));
	}
}
