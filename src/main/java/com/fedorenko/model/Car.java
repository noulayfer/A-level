package com.fedorenko.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public abstract class Car {
    private Color  color;
    private final String id;

    private Engine engine;

    private int count;

    private CarType type;

    public Car() {
        this.id = UUID.randomUUID().toString();
    }

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
    @Override
    public String toString() {
        return String.format("[%s], %s", getId(), getColor());
    }
}
