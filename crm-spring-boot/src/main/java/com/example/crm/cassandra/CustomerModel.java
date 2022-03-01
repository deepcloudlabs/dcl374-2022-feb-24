package com.example.crm.cassandra;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.example.crm.entity.CustomerType;
import com.example.validation.TcKimlikNo;

@Table("customers")
public class CustomerModel {
	@TcKimlikNo
	@PrimaryKeyColumn(name = "tckimlikno", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
	private String identity;
	@Size(min = 5)
	@Column
	private String fullname;
	@Email
	@Column
	private String email;
	@NotBlank
	@Column
	private String phone;
	@Max(2008)
	@Column
	private int birthYear;
	@Column
	private String photo;
	@Column
	private CustomerType type;

	public CustomerModel() {
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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
		CustomerModel other = (CustomerModel) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullname=" + fullname + ", email=" + email + ", phone=" + phone
				+ ", birthYear=" + birthYear + ", type=" + type + "]";
	}

}
