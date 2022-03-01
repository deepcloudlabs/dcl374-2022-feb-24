package com.example.crm.dto.request;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.crm.entity.CustomerType;
import com.example.validation.TcKimlikNo;

public class AddCustomerRequest {
	@TcKimlikNo
	private String identity;
	@Size(min = 5)
	private String fullname;
	@Email
	private String email;
	@NotBlank
	private String phone;
	@Max(2008)
	private int birthYear;
	private byte[] photo;
	private List<AddressRequest> addresses;
	private CustomerType type;

	public AddCustomerRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
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

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddCustomerRequest other = (AddCustomerRequest) obj;
		return Objects.equals(identity, other.identity);
	}

}
