package com.rafaelsouzaf.fbe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class FbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbeApplication.class, args);
		log.info("JAR IS STARTED.");
	}

}
