package com.fedorenko.model;

public class PassengerCar extends Car implements CountRestore {
    private int passengerCount;

    public PassengerCar() {
        super();
    }
    public PassengerCar(final Color color) {
        super(color);
    }

    public PassengerCar(final Color color, final int passengerCount) {
        super(color);
        this.passengerCount = passengerCount;
    }

    @Override
    public void restore() {
        this.count = 100;
        System.out.println("The count is" + count);
    }

    @Override
    public String toString() {
        return super.toString() + " " + passengerCount;
    }
}
