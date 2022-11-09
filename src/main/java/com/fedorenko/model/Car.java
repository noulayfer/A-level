package com.fedorenko.model;

import java.util.Random;
import java.util.UUID;

public class Car {
    private Color  color;
    private final String id;


    public Car(Color color) {
        this.color = color;
        this.id = UUID.randomUUID().toString();
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

    @Override
    public String toString() {
        return String.format("[%s], %s", getId(), getColor());
    }
}
