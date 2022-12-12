package com.fedorenko;


import com.fedorenko.action.Actions;
import com.fedorenko.model.*;
import com.fedorenko.service.CarService;
import com.fedorenko.util.AlgorithmUtil;
import com.fedorenko.util.UserInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static  <T extends Animal> void someMethod(List<T> someList) {
        for (Animal animal : someList) {
            System.out.println(animal);
        }
    }
    private static void someMethod1(List<? extends Animal> someList) {
        for (Animal animal : someList) {
            System.out.println(animal);
        }
    }
    public static void main(String[] args) {
//        String[] names = Actions.mapToNames();
//
//        while (true) {
//            final int userInput = UserInput.menu(names);
//            Actions.values()[userInput].execute();
//        }

//        CarService carService = CarService.getInstance();
//        carService.create(9);
//        carService.printAll();
//        Car[] sortedCars = AlgorithmUtil.bubbleSort2(carService.getAll());
//        System.out.println(Arrays.toString(sortedCars));
//
//        int someInt = AlgorithmUtil.binarySearch(sortedCars, sortedCars[7], 0, sortedCars.length);
//
//        System.out.println(someInt);

//
    }
}
class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}
class Dog extends Animal {
    @Override
    public String toString() {
        return "Dog";
    }
}

class LittleDog extends Dog {
    @Override
    public String toString() {
        return "LittleDog";
    }
}


