package com.inventory.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.ItemType;
import com.inventory.repository.ItemTypeRepository;
import com.inventory.utils.Result;

@Service
public class ItemTypeService {

	@Autowired
	ItemTypeRepository repo;

	public Result<List<ItemType>> getAll() {
		Result<List<ItemType>> result = new Result<List<ItemType>>();
		try {
			List<ItemType> list = new ArrayList<ItemType>();
			repo.findAll().forEach(list::add);
			result.setContent(list);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public String addItemType(String type) {
		try {
			ItemType newType = new ItemType();
			newType.setType(type);
			repo.save(newType);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Success";
	}

	public Result<List<ItemType>> getItemTypes() {// this is with stored procedure
		Result<List<ItemType>> result = new Result<List<ItemType>>();
		try {
			List<ItemType> typeList = repo.getItemTypes();
			result.setContent(typeList);
			result.setMessage("Success");
			return result;
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<String>> getDistinctTypes() {
		Result<List<String>> result = new Result<List<String>>();
		try {
			List<ItemType> types = new ArrayList<ItemType>();
			repo.findAll().forEach(types::add);
			types.forEach(type -> type.getId());
			Set<String> itemTypes = new LinkedHashSet<String>();
			types.forEach(type -> itemTypes.add(type.getType()));
			result.setContent(new ArrayList<>(itemTypes));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public Result<List<ItemType>> getDistinctObjectTypes() {

		Result<List<ItemType>> result = new Result<List<ItemType>>();
		try {
			List<ItemType> types = new ArrayList<ItemType>();
			repo.findAll().forEach(types::add);
			types.forEach(type -> type.getId());
			Set<ItemType> itemTypes = new LinkedHashSet<ItemType>();
			types.forEach(type -> itemTypes.add(type));
			result.setContent(new ArrayList<>(itemTypes));
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public Result<String> insertItemType(HashMap<String,String> itemType) {
		Result<String> result = new Result<String>();
		try {
			String type = itemType.get("type");
			repo.insertItemType(type);
			result.setMessage("Success");
			return result;
		}catch(Exception e) {
			result.setMessage("Failed");
			return result;
		}
	}
}
