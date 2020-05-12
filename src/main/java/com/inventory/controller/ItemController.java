package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Item;
import com.inventory.service.ItemService;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService service;


	@GetMapping("/all")
	public Response getIventory() {
		return new Response(service.getIventoryItems());
	}

	@GetMapping("/allOrderedDesc")
	public Response getIventoryOrderedDesc() {
		return new Response(service.getIventoryItemsOrderedDesc());
	}

	@GetMapping("/")
	public Response getAllItems() {
		return new Response(service.getAll());
	}

	@GetMapping("/filterByStatus")
	public Response filterByStatus(String status) {
		return new Response(service.filterItemsByStatus(status));
	}

	@GetMapping("/history")
	public Response getHistory(int itemId) {
		return new Response(service.getHistory(itemId));
	}
	
	@RequestMapping(value = "/add", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public Response addItemToDb(@RequestBody Item item) {
		Response response = new Response();
		response.setMessage(service.addItem(item));
		response.setOutput(null);
		return response;
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.PUT)
	public Response updateItem(@RequestBody Item item) {
		Response response = new Response();
		response.setMessage(service.updateItem(item));
		return response;
	}

	@RequestMapping(value = "/customFind", produces = "application/json", method = RequestMethod.POST)
	public Response getItemByCustomCrieria(@RequestBody Item bodyItem) {
		return new Response(service.findByCriteria(bodyItem));

	}

	@DeleteMapping("/delete")
	public Response deleteItem(@RequestParam int targetId) {
		Response response = new Response();
		response.setMessage(service.deleteItem(targetId));
		return response;
	}

	@RequestMapping(value = "/update/stateByType", produces = "application/json", method = RequestMethod.POST)
	public Response updateItemStatus(@RequestParam String itemId, @RequestParam String stateType) {
		return new Response(service.updateStateByType(itemId, stateType));
	}
	

	@GetMapping("/producers")
	public Response getProducers() {
		return new Response(service.getAllProducers());
	}

	@GetMapping("/models")
	public Response getModels() {
		return new Response(service.getAllModels());
	}
	
	@RequestMapping(value = "/inventoryNumberList", produces = "application/json", method = RequestMethod.GET)
	public Response getInventoryNumberList() {
		return new Response(service.getInventoryNumberList());
	}
	
	@GetMapping("/serialIds")
	public Response getSerialIds() {
		return new Response(service.getAllSerialIds());
	}
	
	@GetMapping("/getDataset/state")
	public Response getDatasetByState() {
		return new Response(service.getDatasetByState());
	}
	  
	@RequestMapping(value = "/buyout", produces = "application/json", method = RequestMethod.GET)
	public Response getBuyoutInfo() {
		return new Response(service.getBuyoutInfo());
	}
	
	@RequestMapping(value="/recentAssets", produces="application/json", method = RequestMethod.GET)
	public Response getRecentAssets(){
		return new Response(service.getRecentAssets());
	}
	
	@GetMapping("/findByInventoryNumber")
	public Response findByInventoryNumber(String number) {
		return new Response(service.getItemByInventoryNr(number));
	}
}
