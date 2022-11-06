package com.fedorenko;

import com.fedorenko.model.Car;
import com.fedorenko.service.CarService;

public class Main {
    public static void main(String[] args) {
        Car car1 = CarService.create();
        Car car2 = CarService.create();
        Car car3 = CarService.create();
        CarService.print(car1);
        CarService.print(car2);
        CarService.print(car3);
    }
}
