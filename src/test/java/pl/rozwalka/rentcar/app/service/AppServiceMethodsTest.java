package pl.rozwalka.rentcar.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.user.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppServiceMethodsTest {

    User user;

    @BeforeEach
    void init() {
        user = new User(1L, "firstName", "lastName", new ArrayList<>());
    }

    @Test
    void shouldRentCarToUser() {
        //given
        Car car = new Car(1L, "brand", "model", true);
        //when
        AppServiceMethods.setUserAndCarToRent(user, car);
        //then
        assertEquals(car, user.getCars().get(0));
    }

    @Test
    void shouldGiveBackCarFromUser() {
        //given
        Car car = new Car(1L, "brand", "model", false);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        user.setCars(cars);
        //when
        AppServiceMethods.setUserAndCarToGiveBack(user, car);
        //then
        assertEquals(0, user.getCars().size());
    }
}