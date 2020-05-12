package com.inventory.repository;

import java.util.List;

import com.inventory.dto.BuyoutDto;
import com.inventory.model.Item;

public interface ItemRepositoryCustom {

	List<Item> getInventoryItems();

	List<Item> getInventoryItemsOrderedDesc();

	public Boolean addItem(Item item);
	
	List<String> getProducers();
	
	List<BuyoutDto> getBuyoutInfo();
	
	List<Item> getRecentAssets();
	
	
}
