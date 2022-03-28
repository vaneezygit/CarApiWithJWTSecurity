package com.vaneezy.CarAPI;

import com.vaneezy.CarAPI.DAO.CarDAO.CarDAO;
import com.vaneezy.CarAPI.Entity.Car;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Engine;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class CarApiApplication {

	private final CarDAO carDAO;

	public static void main(String[] args) {
		SpringApplication.run(CarApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			List<Car> cars = new ArrayList<>();
			cars.add(new Car(
					null,
					"bebrovoz",
					"bebrocar",
					BigDecimal.valueOf(5000),
					Car.CarType.CARGO,
					Car.EngineType.DIESEL
			));
			cars.add(new Car(
					null,
					"bebra",
					"bebrocar",
					BigDecimal.valueOf(10000),
					Car.CarType.PASSENGER,
					Car.EngineType.DIESEL
			));
			cars.add(new Car(
					null,
					"tachka",
					"toyota",
					BigDecimal.valueOf(3000),
					Car.CarType.PASSENGER,
					Car.EngineType.DIESEL
			));
			cars.add(new Car(
					null,
					"mashina",
					"zalupa",
					BigDecimal.valueOf(8000),
					Car.CarType.CARGO,
					Car.EngineType.ELECTRIC
			));
			cars.add(new Car(
					null,
					"eblya",
					"nissan",
					BigDecimal.valueOf(5001),
					Car.CarType.PASSENGER,
					Car.EngineType.DIESEL
			));
			cars.add(new Car(
					null,
					"jopa",
					"pomidor",
					BigDecimal.valueOf(4999),
					Car.CarType.PASSENGER,
					Car.EngineType.PETROL
			));
			cars.add(new Car(
					null,
					"govno",
					"bebrocar",
					BigDecimal.valueOf(6000),
					Car.CarType.PASSENGER,
					Car.EngineType.PETROL
			));
			cars.add(new Car(
					null,
					"bebrinka",
					"chmo",
					BigDecimal.valueOf(10000),
					Car.CarType.PASSENGER,
					Car.EngineType.DIESEL
			));
			cars.forEach(carDAO::save);
		};
	}
}
