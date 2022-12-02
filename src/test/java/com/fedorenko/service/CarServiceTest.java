package com.fedorenko.service;

import com.fedorenko.model.Car;
import com.fedorenko.model.CarType;
import com.fedorenko.model.Color;
import com.fedorenko.model.PassengerCar;
import com.fedorenko.repository.CarArrayRepository;
import com.fedorenko.util.RandomGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

//initialize
//action
//check

class CarServiceTest {

    private CarService target;
    private CarArrayRepository repository;
    private RandomGenerator randomGenerator;
    private Car car;
    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CarArrayRepository.class);
        target = new CarService(repository);
        randomGenerator = Mockito.mock(RandomGenerator.class);
        car = new PassengerCar();
    }

    @Test
    void create_not_null() {
        final Car car = target.create();
        Assertions.assertNotNull(car);
        Mockito.verify(repository).save(car);
    }

    @Test
    void create_with_random_positive() {
        Mockito.when(randomGenerator.getRandomNumber()).thenReturn(3);
        final int realCount = target.create(randomGenerator);
        final int expectedCount = 3;
        Assertions.assertEquals(expectedCount, realCount);
    }

    @Test
    void create_with_random_negative() {
        Mockito.when(randomGenerator.getRandomNumber()).thenReturn(-3);
        final int realCount = target.create(randomGenerator);
        final int expectedCount = -1;
        Assertions.assertEquals(expectedCount, realCount);
    }

    @Test
    void create_with_null_random_generator() {
        final int expectedCount = -1;
        randomGenerator = null;
        final int realCount = target.create(randomGenerator);
        Assertions.assertEquals(expectedCount, realCount);
    }

    @Test
    void create_with_positive_count() {
        final int count = 7;
        final int realCount = target.create(count);
        final int expectedCount = 7;
        Assertions.assertEquals(expectedCount, realCount);
    }
    @Test
    void create_with_negative_count() {
        final int count = -7;
        final int realCount = target.create(count);
        final int expectedCount = -1;
        Assertions.assertEquals(expectedCount, realCount);
    }

    @Test
    void create_with_zero_count() {
        final int count = 0;
        final int realCount = target.create(count);
        final int expectedCount = -1;
        Assertions.assertEquals(expectedCount, realCount);
    }

    @Test
    void getAll_does_not_throw() {
        Assertions.assertDoesNotThrow(() -> target.getAll());
        Mockito.verify(repository).getAll();
    }

    @Test
    void getAll_is_null() {
        Assertions.assertNull(target.getAll());
        Mockito.verify(repository).getAll();
    }

    @Test
    void getAll_not_null() {
        final Car[] cars = {new PassengerCar(), new PassengerCar()};
        Mockito.when(repository.getAll()).thenReturn(cars);
        Assertions.assertNotNull(target.getAll());
        Mockito.verify(repository).getAll();
    }

    @Test
    void find_with_null_id() {
        Assertions.assertNull(target.find(null));
        Mockito.verify(repository, Mockito.never()).getById(null);
    }

    @Test
    void find_with_empty_id() {
        final String emptyStr = "    ";
        Assertions.assertNull(target.find(emptyStr));
        Mockito.verify(repository, Mockito.never()).getById(emptyStr);
    }
    @Test
    void find_not_null() {
        Mockito.when(repository.getById("random")).thenReturn(car);
        Car car = target.find("random");
        Assertions.assertNotNull(car);
        Mockito.verify(repository).getById(Mockito.anyString());
    }
    @Test
    void delete_with_positive() {
        Assertions.assertDoesNotThrow(() -> target.delete("random"));
        Mockito.verify(repository).delete(Mockito.anyString());
    }

    @Test
    void delete_with_null_id() {
        Assertions.assertDoesNotThrow(() -> target.delete(null));
        Mockito.verify(repository, Mockito.never()).delete(null);
    }

    @Test
    void delete_with_empty_id() {
        final String emptyStr = "    ";
        Assertions.assertDoesNotThrow(() -> target.delete(emptyStr));
        Mockito.verify(repository, Mockito.never()).delete(emptyStr);
    }

    @Test
    void insertCar_with_positive() {
        int randomIndex = 7;
        Assertions.assertDoesNotThrow(() -> target.insertCar(randomIndex, car));
        Mockito.verify(repository).insert(randomIndex, car);
    }
    @Test
    void insertCar_with_incorrect_index() {
        int randomIndex = -3;
        Assertions.assertDoesNotThrow(() -> target.insertCar(randomIndex, car));
        Mockito.verify(repository, Mockito.never()).insert(randomIndex, car);
    }
    @Test
    void insertCar_null_car() {
        int randomIndex = 4;
        Assertions.assertDoesNotThrow(() -> target.insertCar(randomIndex, null));
        Mockito.verify(repository, Mockito.never()).insert(randomIndex, null);
    }

    @Test
    void check_with_positive() {
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    void check_with_null_car() {
        Assertions.assertDoesNotThrow(() -> CarService.check(null));
    }

    @Test
    void printAll() {
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    void carEquals_with_null_car() {
        final boolean expected = false;
        boolean realValue = target.carEquals(car, null);
        Assertions.assertEquals(expected, realValue);
    }

    @Test
    void carEquals_with_same_car() {
        final boolean expected = true;
        boolean realValue = target.carEquals(car, car);
        Assertions.assertEquals(expected, realValue);
    }

    @Test
    void carEquals_cars_with_same_values() {
        final boolean expected = false;
        Car car1 = new PassengerCar(Color.BLACK);
        Car car2 = new PassengerCar(Color.BLACK);
        final boolean realValue = target.carEquals(car1, car2);
        Assertions.assertEquals(expected, realValue);
    }

}