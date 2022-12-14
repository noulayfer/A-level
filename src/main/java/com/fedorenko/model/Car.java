package com.fedorenko.model;

import com.fedorenko.service.CarService;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public abstract class Car implements CountRestore {

    private static final Random RANDOM = new Random();

    private static final CarService CAR_SERVICE = CarService.getInstance();
    private Color  color;
    private String id;

    private Engine engine;

    protected int count;

    private CarType type;

    private String manufacturer;

    private int price;

    public Car(CarType type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.manufacturer = CAR_SERVICE.randomString();
        this.engine = new Engine();
        this.price = RANDOM.nextInt(5000);
    }

    public Car(Color color, CarType type) {
        count = 1;
        this.color = color;
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.manufacturer = CAR_SERVICE.randomString();
        this.engine = new Engine();
        this.price = RANDOM.nextInt(5000);
    }
    public Car(Color color, Engine engine, CarType type) {
        count = 1;
        this.color = color;
        this.engine = engine;
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.manufacturer = CAR_SERVICE.randomString();
        this.engine = new Engine();
        this.price = RANDOM.nextInt(5000);
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
