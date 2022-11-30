package com.fedorenko.model;

public class Truck extends Car implements CountRestore {
    private int loadCapacity;

    public Truck() {
    }

    public Truck(final Color color) {
        super(color);
    }

    public Truck(final Color color, final int loadCapacity) {
        super(color);
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
