package com.marius.converter.common;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class KelvinToCelsiusConverter {

    public KelvinToCelsiusConverter(){}

    public BigDecimal convertKelvinToCelsius(Double kelvin) {
        return new BigDecimal(kelvin - 273.15D).setScale(2, RoundingMode.HALF_UP);
    }
}
