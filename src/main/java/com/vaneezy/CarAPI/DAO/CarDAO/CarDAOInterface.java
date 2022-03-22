package com.vaneezy.CarAPI.DAO.CarDAO;

import com.vaneezy.CarAPI.Entity.Car;

import java.util.List;

public interface CarDAOInterface {

    List<Car> getAll();

    Car getById(Long id);

    void update(Car car, Long id);

    void save(Car car);

    void delete(Long id);
}
