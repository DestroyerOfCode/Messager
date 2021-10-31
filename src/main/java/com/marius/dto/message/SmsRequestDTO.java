package com.marius.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SmsRequestDTO {

    @NotBlank
    private final String phoneNumber; // destination

    @NotBlank
    private final String cityName;

    public SmsRequestDTO(@JsonProperty("phoneNumber") String phoneNumber,
                         @JsonProperty("cityName") String cityName) {
        this.phoneNumber = phoneNumber;
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "SmsRequestDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
