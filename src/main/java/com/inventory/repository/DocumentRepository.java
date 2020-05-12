package com.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Integer> {

}
