package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer>{

}
