package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateMemoryLeak {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("1","jack"));
		employees.add(new Employee("2","kate"));
		System.err.println(employees.size());
		employees.remove(new Employee("2","kate"));
		System.err.println(employees.size());
		employees.remove(new Employee("1","jack"));
		System.err.println(employees.size());
	}

}

class Employee {
	private String identity;
	private String fullname;

	public Employee(String identity, String fullname) {
		this.identity = identity;
		this.fullname = fullname;
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
		return "Employee [identity=" + identity + ", fullname=" + fullname + "]";
	}

}