package pl.rozwalka.rentcar.car.controller;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pl.rozwalka.rentcar.car.entity.Car;
import pl.rozwalka.rentcar.car.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarRepository carRepository;

    private Car newCar;

    @BeforeEach
    void init() {
        newCar = Car.builder()
                .brand("brand")
                .model("model")
                .available(true)
                .build();
    }

    @AfterEach
    void after() {
        carRepository.delete(newCar);
    }

    @Test
    @Transactional
    void shouldGetSingleCarByBrand() throws Exception {
        //given
        carRepository.save(newCar);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/app/cars?brand=brand"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Car.class);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isEqualTo(newCar.getId());
        assertThat(car.getModel()).isEqualTo("model");
    }

    @Test
    @Transactional
    void shouldGetSingleCarByBrandOrModelLike() throws Exception {
        //given
        carRepository.save(newCar);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/app/cars/search?like=el"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        List<Car> carList = objectMapper.readValue(mvcResult
                .getResponse()
                .getContentAsString(), new TypeReference<>() {
        });
        assertThat(carList.get(0)).isNotNull();
        assertThat(carList.get(0).getId()).isEqualTo(newCar.getId());
        assertThat(carList.get(0).getModel()).isEqualTo(newCar.getModel());
        assertEquals(1, carList.size());
    }
}