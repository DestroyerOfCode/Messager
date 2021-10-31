package com.marius.controller.weather;

import com.marius.dto.weather.WeatherDTO;
import com.marius.service.weather.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/current")
    public ResponseEntity<List<WeatherDTO>> getCurrentWeather() {
        List<WeatherDTO> weathersDtoList = weatherService.getCurrentWeather();
        if (weathersDtoList != null && !weathersDtoList.isEmpty())
            return new ResponseEntity<>(weathersDtoList, HttpStatus.OK);
        return new ResponseEntity<>(weathersDtoList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/forecast/daily")
    public ResponseEntity<List<WeatherDTO>> getDailyForecast(@RequestParam(required = true) BigDecimal lat,
                               @RequestParam(required = true) BigDecimal lon,
                               @RequestParam(required = false) String excludedForecasts) {
        List<WeatherDTO> weathersDtoList = weatherService.getDailyForecast(lat, lon, excludedForecasts);
        if (weathersDtoList != null && !weathersDtoList.isEmpty())
            return new ResponseEntity<>(weathersDtoList, HttpStatus.OK);
        return new ResponseEntity<>(weathersDtoList, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{cityName}")
    public ResponseEntity<WeatherDTO> getWeatherByCity(@RequestParam(required = true) String cityName) {
        WeatherDTO weathersDto = weatherService.getWeatherByCity(cityName);
        if (weathersDto != null)
            return new ResponseEntity<>(weathersDto, HttpStatus.OK);
        return new ResponseEntity<>(weathersDto, HttpStatus.NOT_FOUND);
    }
}
