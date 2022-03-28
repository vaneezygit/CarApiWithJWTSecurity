package com.vaneezy.CarAPI.RESTControllers;

import com.vaneezy.CarAPI.Entity.Car;
import com.vaneezy.CarAPI.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController @RequiredArgsConstructor
@RequestMapping("api/v1/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<Car> getAll(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }

    @PostMapping
    public void save(@RequestBody Car car){
        carService.saveCar(car);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Car car, @PathVariable("id") Long id){
        carService.updateCar(car, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id){
        carService.deleteCar(id);
    }

    @GetMapping("/withPriceGreaterThan/{sum}")
    public ResponseEntity<?> carsWithPriceGreaterThan(@PathVariable("sum") BigDecimal sum, @RequestParam(name = "limit") Integer limit, @RequestParam(name = "offset") Integer offset){
        Map<String, Object> result = carService.carsWithPriceGreaterThan(sum, limit, offset);
        return ResponseEntity.ok(result);
    }
}
