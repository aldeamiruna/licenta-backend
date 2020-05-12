package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.Item;

@Repository
public interface ItemRepository
		extends CrudRepository<Item, Integer>, ItemRepositoryCustom, JpaSpecificationExecutor<Item> {

}
