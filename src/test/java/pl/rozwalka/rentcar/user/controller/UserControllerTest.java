package pl.rozwalka.rentcar.user.controller;

import com.fasterxml.jackson.core.type.TypeReference;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

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
                .available(false)
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
    void shouldListUserCars() throws Exception {
        //given
        carRepository.save(car);
        userRepository.save(user);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/app/users/" + user.getId() + "/cars"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<Car> carList = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), new TypeReference<>() {
        });
        assertThat(carList).isNotNull();
        assertThat(carList.get(0).getBrand()).isEqualTo("brand");
    }
}