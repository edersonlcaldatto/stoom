package com.qualification.stoom.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddressDto {

    @NotNull
    private Long id;

    @NotNull
    private String streetName;

    @NotNull
    private String number;

    private String complement;

    @NotNull
    private String neighborhood;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private String zipCode;

    private BigDecimal latitude;

    private BigDecimal longitude;

}
