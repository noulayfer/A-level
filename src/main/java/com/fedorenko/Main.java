package com.fedorenko;


import com.fedorenko.action.Actions;
import com.fedorenko.container.CarList;
import com.fedorenko.container.CarTree;
import com.fedorenko.model.*;
import com.fedorenko.service.CarService;
import com.fedorenko.util.AlgorithmUtil;
import com.fedorenko.util.UserInput;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        PassengerCar passengerCar1 = new PassengerCar();
        PassengerCar passengerCar2 = new PassengerCar();
        PassengerCar passengerCar3 = new PassengerCar();
        PassengerCar passengerCar4 = new PassengerCar();
        PassengerCar passengerCar5 = new PassengerCar();
        PassengerCar passengerCar6 = new PassengerCar();
        passengerCar1.setCount(6);
        passengerCar2.setCount(5);
        passengerCar3.setCount(7);
        passengerCar4.setCount(4);
        passengerCar5.setCount(8);
        passengerCar6.setCount(3);

        CarTree<PassengerCar> carTree = new CarTree<>(passengerCar1);
        carTree.addElement(null, passengerCar2);
        carTree.addElement(null, passengerCar3);
        carTree.addElement(null, passengerCar4);
        carTree.addElement(null, passengerCar5);
        carTree.addElement(null, passengerCar6);
        carTree.print(carTree.getRoot());

        System.out.println(carTree.sumCarCount(carTree.getRoot()));


        Map<String, Object> map = new HashMap<>();
        map.put("count", 35);
        map.put("color", Color.BLACK);
        map.put("type", CarType.CAR);

        Car car = CarService.getInstance().mapToObject(map);
        System.out.println(car);


    }
}

