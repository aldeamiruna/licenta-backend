package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, UserRepositoryCustom,JpaSpecificationExecutor<User>{
	
}
