package com.fedorenko.repository;

import com.fedorenko.model.Car;
import com.fedorenko.model.Color;

public class CarArrayRepository {
    //    CRUD
//    Create (Save, Insert)
//    Read (getById, getAll)
//    Update
//    Delete
    private static Car[] cars = new Car[10];

    public void save(final Car car) {
        int index = putCar(car);
        if (index == cars.length){
            int oldLength = cars.length;
            increaseArray();
            cars[oldLength] = car;
        }
    }
    public Car[] getAll() {
        int newLength = foundLength();
        Car[] newArr = new Car[newLength];
        System.arraycopy(cars, 0, newArr, 0, newLength);
        return newArr;
    }
    public Car getById(final String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }
    public void delete(final String id) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index].getId().equals(id)) {
                break;
            }
        }
        if (index != cars.length) {
            System.arraycopy(cars, index + 1, cars, index, cars.length - (index + 1));
        }
    }
    public void updateColor(String id, Color color) {
        Car car = getById(id);
        if (car != null) {
            car.setColor(color);
        }
    }
    private int putCar(final Car car) {
        int index = 0;
        for (; index < cars.length; index++) {
            if (cars[index] == null) {
                cars[index] = car;
                break;
            }
        }
        return cars.length;
    }
    private void increaseArray() {
        Car[] newArr = new Car[cars.length * 2];
        System.arraycopy(cars, 0, newArr, 0, cars.length);
        cars = newArr;
    }
    private int foundLength() {
        int newLength = 0;
        for (Car car : cars) {
            if (car != null) {
                newLength++;
            } else {
                break;
            }
        }
        return newLength;
    }


}
