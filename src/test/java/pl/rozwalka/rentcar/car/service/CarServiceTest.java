package pl.rozwalka.rentcar.car.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pl.rozwalka.rentcar.car.entity.Car;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarServiceTest {

    @Test
    void shouldEqualsCorrectAvailableCars() {
        //given
        CarService carService = mock(CarService.class);
        //when
        when(carService.getAvailableCars()).thenReturn(mockAvailableCars());
        //then
        assertThat(carService.getAvailableCars(), Matchers.hasSize(3));
    }

    private List<Car> mockAvailableCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1L, "Audi", "A8", true));
        cars.add(new Car(2L, "Mercedes", "AMG", true));
        cars.add(new Car(3L, "BMW", "M5", true));
        return cars;
    }
}
