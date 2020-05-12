package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Room;
import com.inventory.service.RoomService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService service;

	@GetMapping("/")
	public Response getAllRooms() {
		return new Response(service.getAll());
	}

	@RequestMapping(value = "/getItems", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public Response getItems(@RequestBody Room room) {

		return new Response(service.getItems(room.getId()));

	}

}
