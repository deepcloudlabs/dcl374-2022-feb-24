package com.example.crm.document;

import java.util.Objects;

import com.example.crm.entity.AddressType;

// Value Object
public class AddressDocument {
	private String street;
	private String city;
	private String country;
	private String zipCode;
	private AddressType type;

	public AddressDocument() {
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, street, type, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDocument other = (AddressDocument) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(street, other.street) && type == other.type && Objects.equals(zipCode, other.zipCode);
	}

	@Override
	public String toString() {
		return "AddressDocument [street=" + street + ", city=" + city + ", country=" + country + ", zipCode=" + zipCode
				+ ", type=" + type + "]";
	}

}
