package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.inventory.model.Document;
import com.inventory.model.Item;
import com.inventory.service.DocumentService;
import com.inventory.utils.ItemProtocol;
import com.inventory.utils.Response;

@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService service;
	
	@GetMapping("/")
	public com.inventory.utils.Response getTemplates() {
		return new com.inventory.utils.Response(service.getAll());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Response add(@RequestBody Document doc) {
		Response response = new Response();
		response.setMessage(service.saveTemplate(doc));
		return response;
	}

	@RequestMapping(value = "/generate", method = RequestMethod.GET)
	public ResponseEntity<byte[]> generate(String type) {

		byte[] contents = service.generateByType(type).getContent().toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		/* headers.setContentDispositionFormData("default.pdf", "default.pdf");
		 * for download
		 */

		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/generateByTemplate", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateByTemplate(@RequestBody ItemProtocol base) {
		DocumentService.currentTemplate = base;
		service.generateNonEmptyFields();
		byte[] contents = service.generate().getContent().toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		/* headers.setContentDispositionFormData("default.pdf", "default.pdf");
		 * for download
		 */

		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response; 
	}
	
	@RequestMapping(value = "/generateFinal", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<byte[]> generateFinal(@RequestBody ItemProtocol base) {
		DocumentService.currentTemplate = base;
		service.generateFields();
		byte[] contents = service.generate().getContent().toByteArray();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response; 
	}
	
	@GetMapping("/currentTemplate")
	public Response getCurrentTemplate() {
		return new Response(service.getCurrentTemplate());
	}
	
	@RequestMapping(value = "/download/current", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download() {
		service.generateFields();
		byte[] contents = service.generateByTemplate(DocumentService.currentTemplate).getContent().toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentDispositionFormData(DocumentService.currentTemplate.getName() + ".pdf", DocumentService.currentTemplate.getName() + ".pdf");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}
	
	@RequestMapping(value = "/changeItem", produces = "application/json", method = RequestMethod.POST)
	public Response changeItem(@RequestBody Item item) {
		Response response = new Response();
		response.setMessage(service.changeItemFields(item));
		return response;
	}
	
	@RequestMapping(value = "/detachItem", method = RequestMethod.GET)
	public Response detachItem() {
		service.emptyItemFields();
		Response response = new Response();
		response.setMessage("Success");
		return response;
	}
}
