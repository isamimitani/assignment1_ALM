package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
                RestTemplate restTemplate = new RestTemplate();
                 //omvandla från JSON:
                //String res = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random");
                System.out.println("Julia");
	}
}
