package com.vaneezy.CarAPI.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "car_brand")
    private String carBrand;

    private BigDecimal price;

    @Column(name = "car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name = "engine_type")
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
