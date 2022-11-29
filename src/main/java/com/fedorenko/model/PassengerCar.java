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
        passengerCount = 100;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return super.toString() + " " + passengerCount;
    }
}
