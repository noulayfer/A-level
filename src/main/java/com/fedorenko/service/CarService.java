package com.fedorenko.service;

import com.fedorenko.model.Car;

import java.util.Random;

public class CarService {
        static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Car create() {
        String manufacturer = randomString();
        String  engine = randomString();
        String  color = randomString();
        Car car = new Car(manufacturer, engine, color);
        return car;
    }

    private static String randomString() {
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

    public static void print(Car car) {
        System.out.printf("Manufacturer: %s, Engine: %s, Color: %s, Count: %d, Price: %d%n",
                car.getManufacturer(), car.getEngine(), car.getColor(), car.getCount(), car.getPrice());
    }
}
