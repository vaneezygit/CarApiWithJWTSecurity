package com.vaneezy.CarAPI;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class CarApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CarApiApplication.class, args);
	}

}
