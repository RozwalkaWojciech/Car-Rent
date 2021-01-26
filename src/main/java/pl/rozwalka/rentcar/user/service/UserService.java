package pl.rozwalka.rentcar.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<Car> getUserCars(Long id) {
        return userRepository.findById(id).orElseThrow().getCars();
    }
}
