package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.UserStatus;

@Repository
public interface UserStatusRepository extends CrudRepository<UserStatus, Integer>{

}
