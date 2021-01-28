package pl.rozwalka.rentcar.car.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.car.service.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public Car getByModel(@RequestParam String model) {
        return carService.getByBrand(model);
    }

    @GetMapping("available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("search")
    public List<Car> getCarsByBrandModelLike(@RequestParam String like) {
        return carService.getCarsByBrandModelLike(like);
    }
}
