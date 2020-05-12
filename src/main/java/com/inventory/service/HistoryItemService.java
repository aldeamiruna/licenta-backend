package com.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.inventory.model.HistoryItem;
import com.inventory.model.Item;
import com.inventory.repository.HistoryItemRepository;
import com.inventory.utils.Result;

@Service
public class HistoryItemService {
	
	@Autowired
	HistoryItemRepository repo;
	
	public Result<List<HistoryItem>> getAll(){
		Result<List<HistoryItem>> result = new Result<List<HistoryItem>>();
		try {
			List<HistoryItem> list = new ArrayList<HistoryItem>();
			repo.findAll().forEach(list::add);
			result.setContent(list);
			result.setMessage("Success");
		}catch(Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public String addItemRecord(Item item) {//is used to create new records in history for item changes  
		try {
			HistoryItem record = new HistoryItem();
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			record.setDate(date);
			record.setItem(item);
			record.setState(item.getState());
			record.setComment(item.getComment());
			repo.save(record);
		}catch(Exception e) {
			return e.getMessage();
		}
		return "Success";
	}
	
	public Result<List<HistoryItem>> getItemHistory(HashMap<String,Integer> reqBody){
		Result<List<HistoryItem>> result = new Result<List<HistoryItem>>();
		System.out.println(reqBody);
		if(reqBody!=null){
			List<HistoryItem> list = new ArrayList<HistoryItem>();
			Integer itemId = reqBody.get("itemId");
			list = repo.getItemHistory(itemId);
			result.setContent(list);
			result.setMessage("Success");
			
		}else {
			result.setMessage("Fail");
		}
		return result;
	}
}
