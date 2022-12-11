package com.fedorenko;


import com.fedorenko.action.Actions;
import com.fedorenko.model.*;
import com.fedorenko.service.CarService;
import com.fedorenko.util.AlgorithmUtil;
import com.fedorenko.util.UserInput;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
//        String[] names = Actions.mapToNames();
//
//        while (true) {
//            final int userInput = UserInput.menu(names);
//            Actions.values()[userInput].execute();
//        }

        CarService carService = CarService.getInstance();
        carService.create(9);
        carService.printAll();
        Car[] sortedCars = AlgorithmUtil.bubbleSort2(carService.getAll());
        System.out.println(Arrays.toString(sortedCars));

        int someInt = AlgorithmUtil.binarySearch(sortedCars, sortedCars[7], 0, sortedCars.length);
        System.out.println(someInt);

    }
}


