package com.groceteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class groceteriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(groceteriaApplication.class, args);
		System.out.println("Pick Your Need..Online Grocery Store");
	}

}
