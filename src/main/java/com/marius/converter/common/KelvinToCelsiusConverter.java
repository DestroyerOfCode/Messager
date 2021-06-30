package com.marius.converter.common;

import org.springframework.stereotype.Component;

@Component
public class KelvinToCelsiusConverter {

    public KelvinToCelsiusConverter(){}

    public Double convertKelvinToCelsius(Double kelvin) {
        return kelvin - 273.15D;
    }
}
