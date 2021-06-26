package com.marius.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDTO {

    private String cityName;
    private Double temperature;
}
