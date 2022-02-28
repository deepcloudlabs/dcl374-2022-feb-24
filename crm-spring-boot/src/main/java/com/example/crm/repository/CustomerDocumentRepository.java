package com.example.crm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.document.CustomerDocument;

public interface CustomerDocumentRepository 
           extends MongoRepository<CustomerDocument, String> {

}
