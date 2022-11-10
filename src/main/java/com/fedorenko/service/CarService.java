package com.fedorenko.service;

import com.fedorenko.model.Car;
import com.fedorenko.model.Color;
import com.fedorenko.repository.CarArrayRepository;

import java.util.Arrays;
import java.util.Random;


public class CarService {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    private final CarArrayRepository carArrayRepository;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car create() {
        Color color = getRandomColor();
        Car car = new Car(color);
        carArrayRepository.save(car);
        return car;
    }
    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            create();
        }
    }
    public Car[] getAll() {
        return carArrayRepository.getAll();
    }
    public Car find(final String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }
    public void delete(final String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }
    public void insertCar(final int index, final Car car) {
        carArrayRepository.insert(index, car);
    }

    private String randomString() {
        String str = "";
        Random random = new Random();
        int[] randomArr = random.ints(random.ints(1, 3,10)
                        .findAny().getAsInt(), 0, 25).toArray();
        for (int i : randomArr) {
            int j = random.nextInt();
            if (j % 2 == 0) {
                str += String.valueOf(alphabet.charAt(i)).toUpperCase();
            } else {
                str += alphabet.charAt(i);
            }
        }
        return str;
    }
    public static void check(final Car car) {
        if (car.getCount() > 0 && car.getEngine().getPower() > 200) {
            System.out.println("Ready to sell");
        } else if (car.getCount() > 0) {
            System.out.println("Power of engine is not enough");
        } else if (car.getEngine().getPower() > 200) {
            System.out.println("The amount of cars is 0");
        } else {
            System.out.println("Power of engine is not enough and amount of cars is 0");
        }
    }
    public void printAll() {
        final Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
        System.out.println();
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }
}
