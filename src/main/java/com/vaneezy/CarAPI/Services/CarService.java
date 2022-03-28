package com.vaneezy.CarAPI.Services;

import com.vaneezy.CarAPI.DAO.CarDAO.CarDAO;
import com.vaneezy.CarAPI.Entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service @RequiredArgsConstructor
public class CarService {

    private final CarDAO carDAO;

    public List<Car> getAllCars() {
        return carDAO.getAll();
    }

    public Car getCarById(Long id) {
       return carDAO.getById(id);
    }

    public void saveCar(Car car) {
        carDAO.save(car);
    }

    public void updateCar(Car car, Long id) {
        carDAO.update(car, id);
    }

    public void deleteCar(Long id) {
        carDAO.delete(id);
    }

    public Map<String, Object> carsWithPriceGreaterThan(BigDecimal sum, Integer limit, Integer offset) {
        return carDAO.getCarsWherePriceGreaterThan(sum, limit, offset);
    }
}
