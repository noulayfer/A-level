package com.fedorenko.action;

import com.fedorenko.util.UserInput;

import java.util.Optional;

public class CreateAction implements Action {
    private static final int DEFAULT_CARS = 10;

    @Override
    public void execute() {
        final String[] strings = {"Write your amount of cars", "Write default amount of cars"};
        final int userInput = UserInput.menu(strings);

        int numOfCars;
        if (userInput == 0) {
            numOfCars = Optional.of(UserInput.getInt("How many cars do you want to create?"))
                    .filter(x -> x > 0)
                    .orElse(DEFAULT_CARS);
        } else {
            numOfCars = DEFAULT_CARS;
        }
        CAR_SERVICE.create(numOfCars);
        System.out.printf("There were created %d cars %n", numOfCars);
    }
}
