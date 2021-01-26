package pl.rozwalka.rentcar.car.service;

import pl.rozwalka.rentcar.car.entity.Car;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceMethods {

    private CarServiceMethods() {
    }

    public static List<Car> getAvailableCars(List<Car> cars) {
        return cars.stream()
                .filter(Car::isAvailable)
                .collect(Collectors.toList());
    }
}
