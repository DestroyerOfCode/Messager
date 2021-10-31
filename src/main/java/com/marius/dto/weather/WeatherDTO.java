package com.marius.dto.weather;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDTO {

    private String cityName;
    private BigDecimal temperature;
    private BigDecimal lat;
    private BigDecimal lon;
}
