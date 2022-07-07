package com.engobytes.addressor.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestApi {
    @DecimalMin(value = "-90", message = "Latitude has to be provide. Value range from -90 to 90.")
    @DecimalMax(value = "90", message = "Latitude has to be provide. Value range from -90 to 90.")
    private double latitude;

    @DecimalMin(value = "-180", message = "Longitude has to be provide. Value range: from -180 to 180.")
    @DecimalMax(value = "180", message = "Longitude has to be provide. Value range: from -180 to 180.")
    private double longitude;
}
