package com.vaneezy.CarAPI.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "car_brand")
    private String carBrand;

    private BigDecimal price;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "engineType")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    public enum CarType{
        @JsonProperty("cargo")
        CARGO,
        @JsonProperty("passenger")
        PASSENGER,
        @JsonProperty("cargo-passenger")
        CARGO_PASSENGER
    }

    public enum EngineType{
        @JsonProperty("diesel")
        DIESEL,
        @JsonProperty("petrol")
        PETROL,
        @JsonProperty("electric")
        ELECTRIC
    }
}
