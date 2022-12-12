package com.fedorenko.container;

import com.fedorenko.model.Car;
import com.fedorenko.service.CarService;

import java.util.Random;

public class GenericContainer<T extends Car> {
    private static final CarService carService = CarService.getInstance();
    private static final Random random = new Random();

    public void print(T t) {
        carService.printInfo(t);
    }
    public void increaseCount(T t) {
        int valueToIncrease = random.ints(1, 100, 301)
                .findAny().getAsInt();
        t.setCount(t.getCount() + valueToIncrease);
    }

    public <V extends Number> void increaseCount(V num, T t) {
        t.setCount(t.getCount() + (int)num);
    }
}
