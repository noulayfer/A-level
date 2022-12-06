package com.fedorenko;

import com.fedorenko.model.*;
import com.fedorenko.repository.CarArrayRepository;
import com.fedorenko.service.CarService;

public class Main {
    public static void main(String[] args) {
       CarService carService = new CarService(new CarArrayRepository());
       final PassengerCar passengerCar = carService.createPassengerCar();
       final Truck truck = carService.createTruck();
       System.out.println(passengerCar);
       System.out.println(truck);
    }
}
