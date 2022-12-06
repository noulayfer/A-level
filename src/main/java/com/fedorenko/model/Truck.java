package com.fedorenko.model;

public class Truck extends Car {
    private int loadCapacity;

    public Truck() {
        super(CarType.TRUCK);
    }

    public Truck(final Color color) {
        super(color, CarType.TRUCK);
    }

    public Truck(final Color color, final Engine engine) {
        super(color, engine, CarType.TRUCK);
    }

    public Truck(final Color color, final int loadCapacity) {
        super(color, CarType.TRUCK);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void restore() {
        this.count = 50;
        System.out.print("The count is" + count);
    }

    @Override
    public String toString() {
        return super.toString() + " " + loadCapacity;
    }
}
