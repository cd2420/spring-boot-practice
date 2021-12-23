package com.example.point;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PointApplication {

	public static void main(String[] args) {
		log.info("application arguments : " + String.join(",", args));
		SpringApplication.run(PointApplication.class, args);
	}

}
