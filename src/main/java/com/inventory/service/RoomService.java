package com.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.HistoryItem;
import com.inventory.model.Item;
import com.inventory.model.Room;
import com.inventory.repository.RoomRepository;
import com.inventory.utils.Result;

@Service
public class RoomService {

	@Autowired
	RoomRepository repo;

	public Result<List<Room>> getAll() {
		Result<List<Room>> result = new Result<List<Room>>();
		try {
			List<Room> list = new ArrayList<Room>();
			repo.findAll().forEach(list::add);
			result.setContent(list);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	}

	public Result<List<Item>> getItems(int id) {
		Result<List<Item>> result = new Result<List<Item>>();
		try {
			List<Item> items = new ArrayList<Item>();
			Room room = repo.findById(id).get();
			room.getItems().forEach(items::add);
			result.setContent(items);
			result.setMessage("Success");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
		}
		return result;
	
	}
}
