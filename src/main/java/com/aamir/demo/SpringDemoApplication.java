package com.aamir.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		System.out.println("args = " + Arrays.deepToString(args));
		SpringApplication.run(SpringDemoApplication.class, args);
	}

}
