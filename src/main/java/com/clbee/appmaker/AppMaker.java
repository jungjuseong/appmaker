package com.clbee.appmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
		(exclude = HibernateJpaAutoConfiguration.class )
public class AppMaker {

	public static void main(String[] args) {
		SpringApplication.run(AppMaker.class, args);
	}
}
