package com.example.domain;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 2L;	
	private String street;
	private String country;

	public Address() {
	}

	public Address(String street) {
		this.street = street;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
