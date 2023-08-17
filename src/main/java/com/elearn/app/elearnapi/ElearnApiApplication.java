package com.elearn.app.elearnapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elearn.app.elearnapi.utilities.PrintUtilities;

@SpringBootApplication
public class ElearnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearnApiApplication.class, args);

		PrintUtilities.println("Swagger UI at : http://localhost:8080/api/swagger-ui/index.html");
	}

}
