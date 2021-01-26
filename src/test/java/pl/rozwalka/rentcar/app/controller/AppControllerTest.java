package pl.rozwalka.rentcar.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.car.repository.CarRepository;
import pl.rozwalka.rentcar.user.entity.User;
import pl.rozwalka.rentcar.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    private User user;
    private Car car;

    @BeforeEach
    void init() {
        List<Car> cars = new ArrayList<>();

        car = Car.builder()
                .brand("brand")
                .model("model")
                .available(true)
                .build();

        cars.add(car);

        user = User.builder()
                .firstName("name")
                .lastName("last")
                .cars(cars)
                .build();
    }

    @AfterEach
    void after() {
        userRepository.delete(user);
        carRepository.delete(car);
    }

    @Test
    @Transactional
    void shouldRentCarForUser() throws Exception {
        //given
        carRepository.save(car);
        userRepository.save(user);
        //when
        MvcResult mvcResult = mockMvc.perform(put("/app/" + user.getId() + "/rent?carId=" + car.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        User testUser = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), User.class);
        assertThat(testUser).isNotNull();
        assertThat(testUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(testUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(testUser.getCars().get(0).getBrand()).isEqualTo(car.getBrand());
        assertThat(testUser.getCars().get(0).getModel()).isEqualTo(car.getModel());
        assertThat(testUser.getCars().get(0).isAvailable()).isFalse();
    }
}