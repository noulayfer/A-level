package com.fedorenko.action;

import com.fedorenko.util.UserInput;

public class FindAction implements Action {
    @Override
    public void execute() {
        int userInput = -1;
        do {
            if (CAR_SERVICE.getAll().length <= 0) {
                System.out.println("The array is empty");
                return;
            }
            String str = String.format("Write which car do you want to find from 0 to %d%n",
                    CAR_SERVICE.getAll().length - 1);
            userInput = UserInput.getInt(str);
        } while (userInput < 0 || userInput >= CAR_SERVICE.getAll().length);
        System.out.printf("Info about car with number: %d%n", userInput);
        CAR_SERVICE.printInfo(CAR_SERVICE.getAll()[userInput]);
    }
}
