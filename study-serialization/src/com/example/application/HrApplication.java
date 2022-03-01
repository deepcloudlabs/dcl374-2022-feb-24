package com.example.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.example.domain.Address;
import com.example.domain.Employee;

public class HrApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		var jack = new Employee("1", "jack bauer", "TR1", 10_000);
		jack.setAddresses(List.of(new Address("home address")));
		// Text IO    -> char[] ...Reader, ...Writer
		// Binary IO  -> byte[] ...InputStream,...OutputStream
		var oos = new ObjectOutputStream(new FileOutputStream(new File("src","jack.data")));
		oos.writeObject(jack);
		oos.close();
	}

}
