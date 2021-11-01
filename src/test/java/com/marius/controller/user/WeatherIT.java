package com.marius.controller.user;

import com.marius.controller.weather.WeatherController;
import com.marius.dto.weather.WeatherDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherIT {

    @Autowired
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
    }

    @Disabled
    @Test
    void getWeatherForecast() {
        List<WeatherDTO> forecastWeathers = new ArrayList<>();
        ResponseEntity<List<WeatherDTO>> RRWeathers = weatherController.getDailyForecast(
                new BigDecimal("48.1667"), new BigDecimal("18.3333"), "Current,Hourly,Minutely");
        if (HttpStatus.OK == RRWeathers.getStatusCode())
            forecastWeathers = RRWeathers.getBody();
        else
            Assertions.fail();

        Assertions.assertNotNull(forecastWeathers);
    }

    @Disabled
    @Test
    void getWeatherByCity() {
        WeatherDTO weatherDTO = new WeatherDTO();
        ResponseEntity<WeatherDTO> RRWeather = weatherController.getWeatherByCity("Nitra");
        if (HttpStatus.OK == RRWeather.getStatusCode())
            weatherDTO = RRWeather.getBody();
        else
            Assertions.fail();

        Assertions.assertNotNull(weatherDTO);
    }
}
