package com.fedorenko.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public abstract class Car {
    private Color  color;
    private final String id;

    private Engine engine;

    protected int count;

    private CarType type;

    public Car(CarType type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }

    public Car(Color color, CarType type) {
        count = 1;
        this.color = color;
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }
    public Car(Color color, Engine engine, CarType type) {
        count = 1;
        this.color = color;
        this.engine = engine;
        this.id = UUID.randomUUID().toString();
        this.type = type;
    }
    @Override
    public String toString() {
        return String.format("[%s], %s", getId(), getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return count == car.count && color == car.color && type.equals(car.type) && id.equals(car.id) && Objects.equals(engine, car.engine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, id, engine, count, type);
    }
}
