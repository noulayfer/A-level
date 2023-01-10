package com.fedorenko.repository;

import com.fedorenko.model.Car;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CarListRepository implements RepositoryInterface<Car> {
    private static List<Car> cars = new LinkedList<>();
    private static CarListRepository carListRepository;

    private static final BiPredicate<Car, String> CHECK_ID = (car, id) -> car.getId().equals(id);

    private CarListRepository() {
    }

    public static CarListRepository getInstance() {
        cars = new LinkedList<>();
        if (carListRepository == null) {
            return new CarListRepository();
        }
        return carListRepository;
    }

    @Override
    public void save(Car car) {
        cars.stream()
                .filter(carsCar -> CHECK_ID.test(carsCar, car.getId()))
                .findAny()
                .ifPresentOrElse(
                        carsCar -> carsCar.setCount(car.getCount()),
                        () -> cars.add(car)
                );
    }

    @Override
    public List<Car> getAll() {
        return cars;
    }

    @Override
    public Car getById(String id) {
        return cars.stream()
                .filter(carsCar -> CHECK_ID.test(carsCar, id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public void delete(String id) {
        cars.removeIf(carsCar -> CHECK_ID.test(carsCar, id));
    }

    @Override
    public void insert(int index, Car car) {
        cars.add(index, car);
    }
}
