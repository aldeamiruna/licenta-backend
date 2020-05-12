package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer>, StateRepositoryCustom{

}
