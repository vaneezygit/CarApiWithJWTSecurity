package com.vaneezy.CarAPI.DAO.CarDAO;

import com.vaneezy.CarAPI.DAO.Validator;
import com.vaneezy.CarAPI.Entity.Car;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CarValidator {

    public static Map<String, Object> validateCar(Car car){
        Validator<Car> validator = carToValidate -> {
            Map<String, Object> fieldMap = new HashMap<>();
            fieldMap.put("model", carToValidate.getModel());
            fieldMap.put("carBrand", carToValidate.getCarBrand());
            fieldMap.put("price", carToValidate.getPrice());
            fieldMap.put("carType", carToValidate.getCarType());
            fieldMap.put("engineType", carToValidate.getEngineType());

            return fieldMap.keySet()
                    .stream()
                    .filter(key -> fieldMap.get(key) != null)
                    .collect(Collectors.toMap(key -> key, fieldMap::get));
        };
        return validator.validate(car);
    }
}
