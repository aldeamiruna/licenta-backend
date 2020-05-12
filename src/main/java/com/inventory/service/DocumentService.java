package com.inventory.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.Document;
import com.inventory.model.HistoryItem;
import com.inventory.model.Item;
import com.inventory.repository.DocumentRepository;
import com.inventory.utils.ItemProtocol;
import com.inventory.utils.PDFFactory;
import com.inventory.utils.PDFField;
import com.inventory.utils.Result;

@Service
public class DocumentService {
	
	@Autowired
	private DocumentRepository repo;

	public static ItemProtocol currentTemplate;
	
	public static LinkedHashMap<String, String> itemFields = new LinkedHashMap<String, String>();
	
	public String saveTemplate(Document doc) {
		try {
			repo.save(doc);
			return "Success";
		}catch(Exception e) {
			return e.getMessage();
		}
	}
	
	public Result<List<Document>> getAll(){
		Result<List<Document>> result = new Result<List<Document>>();
		try {
			List<Document> list = new ArrayList<Document>();
			repo.findAll().forEach(list::add);
			result.setContent(list);
			result.setMessage("Success");
		}catch(Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public Result<ByteArrayOutputStream> generate() {
		Result<ByteArrayOutputStream> result = new Result<ByteArrayOutputStream>();
		try {
			result.setContent(PDFFactory.generateDocument(currentTemplate));
			result.setMessage("Success");
			return result;
		}catch(Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
	}
	
	public Result<ByteArrayOutputStream> generateByType(String type) {
		Result<ByteArrayOutputStream> result = new Result<ByteArrayOutputStream>();
		ItemProtocol base = new ItemProtocol();
		try {
			List<Document> docs = ((Collection<Document>) repo.findAll()).stream()
					.filter(doc -> doc.getName().equals(type))
					.collect(Collectors.toList());
			base.getDataFromString(docs.get(0).getTemplate());
			result.setContent(PDFFactory.generateDocument(base));
			result.setMessage("Success");
			currentTemplate = base;
			return result;
		}catch(Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
	}
	
	public Result<ByteArrayOutputStream> generateByTemplate(ItemProtocol template) {
		Result<ByteArrayOutputStream> result = new Result<ByteArrayOutputStream>();
		try {
			result.setContent(PDFFactory.generateDocument(template));
			result.setMessage("Success");
			currentTemplate = template;
			return result;
		}catch(Exception e) {
			result.setMessage(e.getMessage());
			return result;
		}
	}
	
	public Result<ItemProtocol> getCurrentTemplate(){
		Result<ItemProtocol> result = new Result<ItemProtocol>();
		result.setContent(currentTemplate);
		result.setMessage("Success");
		return result;
	}
	
	public void generateFields() {
		currentTemplate.getSubtitle().generate();
		for(PDFField field : currentTemplate.getFields()) {
			field.generate();
		}
	}
	
	public void generateNonEmptyFields() {
		currentTemplate.getSubtitle().generateNonEmpty();
		for(PDFField field : currentTemplate.getFields()) {
			field.generateNonEmpty();
		}
	}
	
	public void setItemFields(Item item) {
		itemFields.put("comment", item.getComment());
		itemFields.put("inventory-number", Long.toString(item.getInventoryNumber()));
		itemFields.put("model", item.getModel());
		itemFields.put("producer", item.getProducer());
		itemFields.put("serial-id", item.getSerialId());
		itemFields.put("state", item.getState().getType());
		itemFields.put("user-id", item.getUser().getCompanyId());
		itemFields.put("type", item.getItemType().getType());
		if(item.getRoom() != null) {
			itemFields.put("room", item.getRoom().getName());
		}else {
			itemFields.put("room",  null);
		}
	}
	
	public void emptyItemFields() {
		itemFields.clear();
	}
	
	public String changeItemFields(Item item) {
		emptyItemFields();
		setItemFields(item);
		return "Success";
	}
	
}
