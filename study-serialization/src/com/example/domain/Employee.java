package com.example.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String identity;
	private String fullname;
	private String iban;
	private double salary;
	private List<Address> addresses;
	
	public Employee() {
	}

	public Employee(String identity, String fullname, String iban, double salary) {
		this.identity = identity;
		this.fullname = fullname;
		this.iban = iban;
		this.salary = salary;
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

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", iban=" + iban + ", salary=" + salary
				+ "]";
	}

}
