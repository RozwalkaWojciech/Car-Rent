package pl.rozwalka.rentcar.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/users")
public class UserController {

    private final UserService userService;

    @GetMapping("{userId}/cars")
    public List<Car> listOfUserCars(@PathVariable Long userId) {
        return userService.getUserCars(userId);
    }
}
