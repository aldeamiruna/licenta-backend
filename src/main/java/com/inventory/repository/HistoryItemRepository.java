package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.HistoryItem;

@Repository
public interface HistoryItemRepository extends CrudRepository<HistoryItem, Integer>,HistoryItemRepositoryCustom{

}
