package com.logBook.LogbookBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories() // check why this line breaks the program
public class LogbookBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(LogbookBackendApplication.class, args);
	}

}
