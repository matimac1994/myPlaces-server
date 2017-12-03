package com.maciejak.myplaces_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPlacesServerApplication {

	public static final String BASE_URL = "/api/myplaces";

	public static void main(String[] args) {
		SpringApplication.run(MyPlacesServerApplication.class, args);
	}
}
