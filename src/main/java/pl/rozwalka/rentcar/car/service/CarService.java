package pl.rozwalka.rentcar.car.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.car.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car getByBrand(String brand) {
        return carRepository.findCarByBrand(brand).orElseThrow();
    }

    public List<Car> getAvailableCars() {
        return CarServiceMethods.getAvailableCars(carRepository.findAll());
    }

    public List<Car> getCarsByBrandModelLike(String like){
        return carRepository.findByBrandOrModelLike(like);
    }

}
