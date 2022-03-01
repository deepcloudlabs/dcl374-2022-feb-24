package com.example.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.example.domain.Employee;

public class ReadJackFromFile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		var ois = new ObjectInputStream(new FileInputStream(new File("src","jack.data")));
		var jack = (Employee) ois.readObject();
		System.out.println(jack);
		ois.close();
	}

}
