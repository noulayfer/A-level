package com.fedorenko.service;

import com.fedorenko.model.*;
import com.fedorenko.repository.CarArrayRepository;
import com.fedorenko.util.RandomGenerator;

import javax.sound.midi.Track;
import java.util.Arrays;
import java.util.Random;

import static com.fedorenko.model.CarType.*;


public class CarService {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final Random random = new Random();
    private final CarArrayRepository carArrayRepository;

    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public Car create() {
        final Color color = getRandomColor();
        final Car car = new PassengerCar();
        carArrayRepository.save(car);
        return car;
    }

    public int create(final int count) {
        if (count <= 0) {
            return -1;
        }
        for (int i = 0; i < count; i++) {
            create();
        }
        return count;
    }

    public int create(final RandomGenerator randomGenerator) {
        if (randomGenerator == null) {
            return -1;
        }
        int count = randomGenerator.getRandomNumber();
        if (count <= 0) {
            return -1;
        }
        create(count);
        printAll();
        return count;
    }

    public Car createCar(CarType carType) {
        final Color color = getRandomColor();
        final Car car;
        if (carType.equals(CAR)) {
            car = new PassengerCar(color);
        } else if (carType.equals(TRUCK)) {
            car = new Truck(color);
        } else {
            car = null;
        }
        return car;
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public Car find(final String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(final String id) {
        if (id == null || id.isBlank()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void insertCar(final int index, final Car car) {
        if (car == null || index < 0) {
            return;
        }
        carArrayRepository.insert(index, car);
    }

    public boolean carEquals(Car car1, Car car2) {
        if (car1 == null || car2 == null) {
            return false;
        }
        if (car1.getType() != car2.getType()) {
            return false;
        }
        if (car1.hashCode() != car2.hashCode()) {
            return false;
        }
        return car1.equals(car2);
    }


    private String randomString() {
        String str = "";
        Random random = new Random();
        int[] randomArr = random.ints(random.ints(1, 3, 10)
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
        if (car == null) {
            return;
        }
        if (car.getEngine() == null) {
            System.out.println("Engine is null");
            return;
        }
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
