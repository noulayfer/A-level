package com.fedorenko.model;

import java.util.Random;
import java.util.UUID;

public class Car {
    private Color  color;
    private final String id;

    private Engine engine;

    private int count;

    public Car(Color color) {
        count = 1;
        this.color = color;
        this.id = UUID.randomUUID().toString();
    }
    public Car(Color color, Engine engine) {
        count = 1;
        this.color = color;
        this.engine = engine;
        this.id = UUID.randomUUID().toString();
    }
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("[%s], %s", getId(), getColor());
    }
}
