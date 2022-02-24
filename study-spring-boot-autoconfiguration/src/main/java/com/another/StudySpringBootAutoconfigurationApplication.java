package com.another;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// -Dspring.profiles.active=preprod
@SpringBootApplication
@ComponentScan({
	"com.example"
})
public class StudySpringBootAutoconfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySpringBootAutoconfigurationApplication.class, args);
	}

}
