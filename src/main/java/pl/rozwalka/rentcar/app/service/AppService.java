package pl.rozwalka.rentcar.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.car.repository.CarRepository;
import pl.rozwalka.rentcar.user.entity.User;
import pl.rozwalka.rentcar.user.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AppService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Transactional
    public User rentCarByUser(Long userId, Long carId) {
        User user = userRepository.findById(userId).orElseThrow();
        Car car = carRepository.findById(carId).orElseThrow();
        AppServiceMethods.setUserAndCarToRent(user, car);
        return user;
    }

    @Transactional
    public User giveBackCarByUser(Long userId, Long carId) {
        User user = userRepository.findById(userId).orElseThrow();
        Car car = carRepository.findById(carId).orElseThrow();
        AppServiceMethods.setUserAndCarToGiveBack(user, car);
        return user;
    }
}
