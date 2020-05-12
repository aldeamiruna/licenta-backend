package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.ItemType;

@Repository
public interface ItemTypeRepository extends CrudRepository<ItemType, Integer>, ItemTypeRepositoryCustom{

}
