package com.maciejak.myplaces_server;

import com.maciejak.myplaces_server.utils.TopPlacesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyPlacesServerApplication implements CommandLineRunner {

	public static final String BASE_URL = "/api/myplaces";

	private TopPlacesParser topPlacesParser;

	public MyPlacesServerApplication(TopPlacesParser topPlacesParser) {
		this.topPlacesParser = topPlacesParser;
	}

	public static void main(String[] args) {
		SpringApplication.run(MyPlacesServerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
//		topPlacesParser.fillTopPlaces();
	}
}
