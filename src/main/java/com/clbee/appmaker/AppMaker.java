package com.clbee.appmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({
		"com.clbee.appmaker.model",
		"com.clbee.appmaker.jpa" })
public class AppMaker {

	public static void main(String[] args) {
		SpringApplication.run(AppMaker.class, args);
	}
}
