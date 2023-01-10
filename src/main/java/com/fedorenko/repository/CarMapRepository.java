package com.fedorenko.repository;

import com.fedorenko.model.Car;

import java.lang.management.OperatingSystemMXBean;
import java.util.*;
import java.util.function.BiPredicate;

public class CarMapRepository implements RepositoryInterface<Car> {
    private static Map<String, Car> cars = new HashMap<>();
    private static CarMapRepository carMapRepository;

    private CarMapRepository() {
    }

    public static CarMapRepository getInstance() {
        cars = new HashMap<>();
        if (carMapRepository == null) {
            return new CarMapRepository();
        }
        return carMapRepository;
    }
    @Override
    public void save(Car car) {
        Optional.of(cars.get(car.getId()))
                .ifPresentOrElse(x -> x.setCount(car.getCount()),
                        () -> cars.put(car.getId(), car));
    }

    @Override
    public List<Car> getAll() {
        return cars.values().stream().toList();
    }

    @Override
    public Car getById(String id) {
        return Optional.of(cars.get(id)).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void delete(String id) {
        cars.remove(Optional.of(cars.get(id)).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public void insert(int index, Car car) {
        cars.put(car.getId(), car);
    }
}
