package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crm.entity.Customer;

public interface CustomerRepository 
           extends JpaRepository<Customer, String> {

}
