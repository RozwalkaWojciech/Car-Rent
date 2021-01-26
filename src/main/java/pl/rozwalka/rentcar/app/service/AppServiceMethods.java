package pl.rozwalka.rentcar.app.service;

import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.user.entity.User;

import java.util.List;

public class AppServiceMethods {

    private AppServiceMethods() {
    }

    public static void setUserAndCarToRent(User user, Car car) {
        List<Car> userCars = user.getCars();
        if (car.isAvailable()) {
            userCars.add(car);
            user.setCars(userCars);
            car.setAvailable(!car.isAvailable());
        } else throw new IllegalArgumentException("Car is not available to rent");
    }

    public static void setUserAndCarToGiveBack(User user, Car car) {
        List<Car> userCars = user.getCars();
        if (!car.isAvailable()) {
            userCars.remove(car);
            user.setCars(userCars);
            car.setAvailable(!car.isAvailable());
        } else throw new IllegalArgumentException("Car is already given back");
    }
}
