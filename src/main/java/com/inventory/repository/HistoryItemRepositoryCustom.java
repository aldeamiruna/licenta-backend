package com.inventory.repository;

import java.util.List;

import com.inventory.model.HistoryItem;

public interface HistoryItemRepositoryCustom {
	
	public List<HistoryItem> getItemHistory(Integer itemId);
	
}
