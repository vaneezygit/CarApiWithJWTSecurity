package com.vaneezy.CarAPI.DAO;

import java.util.Map;

@FunctionalInterface
public interface Validator<T> {

    Map<String, Object> validate(T t);

}
