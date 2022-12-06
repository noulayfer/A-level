package com.fedorenko.repository;

import com.fedorenko.model.Car;
import com.fedorenko.model.Color;
import com.fedorenko.model.PassengerCar;
import com.fedorenko.service.CarService;
import com.fedorenko.util.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CarArrayRepositoryTest {
    private CarArrayRepository target;
    private Car car;


    @BeforeEach
    void setUp() {
        target = CarArrayRepository.getInstance();
        car = new PassengerCar();
    }

    @Test
    void save_with_positive_car() {
        target.save(car);
        Assertions.assertEquals(car, target.getAll()[0]);
    }

    @Test
    void save_with_increasing() {
        int length = 10;
        for (int i = 0; i < length; i++) {
            target.save(car);
        }
        Assertions.assertDoesNotThrow(() -> target.save(new PassengerCar()));
    }

    @Test
    void save_with_null() {
        Assertions.assertDoesNotThrow(() -> target.save(null));
    }

    @Test
    void getAll_with_null() {
        Assertions.assertNull(target.getAll());
    }

    @Test
    void getAll_with_positive() {
        Car car1 = new PassengerCar();
        target.save(car1);
        Car car2 = target.getAll()[0];
        Assertions.assertEquals(car1, car2);
    }

    @Test
    void getById_with_null() {
        Assertions.assertDoesNotThrow(() -> target.getById(null));
    }

    @Test
    void getById_with_incorrect_id() {
        target.save(car);
        Assertions.assertDoesNotThrow(() -> target.getById("randomString"));
    }

    @Test
    void getById_with_positive_id() {
        target.save(car);
        Assertions.assertEquals(car, target.getById(car.getId()));
    }

    @Test
    void delete_with_null() {
        Assertions.assertDoesNotThrow(() -> target.delete(null));
    }

    @Test
    void delete_with_incorrect_id() {
        Assertions.assertDoesNotThrow(() -> target.delete("random"));
    }

    @Test
    void delete_positive_id() {
        target.save(car);
        target.delete(car.getId());
        Assertions.assertNull(target.getAll());
    }

    @Test
    void insert_with_null_car() {
        Assertions.assertDoesNotThrow(() -> target.insert(3, null));
    }

    @Test
    void insert_with_incorrect_index() {
        Assertions.assertDoesNotThrow(() -> target.insert(-5, car));
    }

    @Test
    void insert_with_positive() {
        target.save(car);
        Car car1 = new PassengerCar();
        target.insert(8, car1);
        int expectedPosition = 1;
        Assertions.assertEquals(car1, target.getAll()[expectedPosition]);
    }

    @Test
    void updateColor_with_null_id() {
        Assertions.assertDoesNotThrow(() -> target.updateColor(null, Color.RED));
    }

    @Test
    void updateColor_with_null_color() {
        Assertions.assertDoesNotThrow(() -> target.updateColor(car.getId(), null));
    }
    @Test
    void updateColor_with_positive() {
        target.save(car);
        target.getAll()[0].setColor(Color.BLACK);
        target.updateColor(car.getId(), Color.RED);
        Assertions.assertEquals(Color.RED, car.getColor());
    }
}