package com.example.crm.dto.response;

import java.util.List;

import com.example.crm.entity.CustomerType;

public class UpdateCustomerResponse {
	private String fullname;
	private String email;
	private String phone;
	private byte[] photo;
	private List<AddressResponse> addresses;
	private CustomerType type;

	public UpdateCustomerResponse() {
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<AddressResponse> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressResponse> addresses) {
		this.addresses = addresses;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
