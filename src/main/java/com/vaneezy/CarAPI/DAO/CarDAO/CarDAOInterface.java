package com.vaneezy.CarAPI.DAO.CarDAO;

import com.vaneezy.CarAPI.Entity.Car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CarDAOInterface {

    List<Car> getAll();

    Car getById(Long id);

    void update(Car car, Long id);

    void save(Car car);

    void delete(Long id);

    Map<String, Object> getCarsWherePriceGreaterThan(BigDecimal param, Integer limit, Integer offset);
}
