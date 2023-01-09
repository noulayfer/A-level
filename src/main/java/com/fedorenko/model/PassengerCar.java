package com.fedorenko.model;

public class PassengerCar extends Car {
    private int passengerCount;

    public PassengerCar() {
        super(CarType.CAR);
    }
    public PassengerCar(final Color color) {
        super(color, CarType.CAR);
    }

    public PassengerCar(final Color color, final Engine engine) {
        super(color, engine, CarType.CAR);
    }

    public PassengerCar(final Color color, final int passengerCount) {
        super(color, CarType.CAR);
        this.passengerCount = passengerCount;
    }

    @Override
    public void restore() {
        this.count = 100;
        System.out.println("The count is" + count);
    }

    @Override
    public String toString() {
        return super.toString() + " " + passengerCount + " " + getCount();
    }
}
