package com.inventory.repository;

import java.util.List;

import com.inventory.model.ItemType;

public interface ItemTypeRepositoryCustom {

	List<ItemType> getItemTypes();

	public void insertItemType(String itemType);
}
