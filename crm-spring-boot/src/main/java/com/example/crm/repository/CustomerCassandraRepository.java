package com.example.crm.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.crm.cassandra.CustomerModel;

public interface CustomerCassandraRepository 
           extends CassandraRepository<CustomerModel, String> {

}
