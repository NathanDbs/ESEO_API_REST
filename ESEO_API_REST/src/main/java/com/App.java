package com;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
		
		
		
		try {
			SpringApplication.run(App.class,args);
			System.out.println("Application demaree !");
		} catch(Exception e) {
			System.out.println("Application erreur : " + e);
		}

	}

}
