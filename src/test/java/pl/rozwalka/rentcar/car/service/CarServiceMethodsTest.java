package pl.rozwalka.rentcar.car.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.rozwalka.rentcar.car.entity.Car;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarServiceMethodsTest {

    private List<Car> cars;

    @BeforeEach
    void init() {
        cars = new ArrayList<>();
        cars.add(new Car(1L, "Audi", "A8", true));
        cars.add(new Car(2L, "Mercedes", "AMG", false));
        cars.add(new Car(3L, "BMW", "M5", true));
    }

    @Test
    void shouldReturnCorrectNumberOfAvailableCars() {
        assertEquals(2, CarServiceMethods.getAvailableCars(cars).size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldReturnTrueForAvailableCar(int id) {
        assertTrue(CarServiceMethods.getAvailableCars(cars).get(id).isAvailable());
    }
}