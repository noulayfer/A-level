package com.fedorenko;

import com.fedorenko.model.Car;
import com.fedorenko.model.Color;
import com.fedorenko.model.Engine;
import com.fedorenko.repository.CarArrayRepository;
import com.fedorenko.service.CarService;

public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService(new CarArrayRepository());
        Car car1 = carService.create();
        car1.setEngine(new Engine());
        Car car2 = carService.create();
        car2.setEngine(new Engine());
        CarService.check(car1);
        CarService.check(car2);
    }
}
