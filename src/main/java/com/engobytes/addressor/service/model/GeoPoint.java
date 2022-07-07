package com.engobytes.addressor.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeoPoint {
    private double longitude;
    private double latitude;
    private String description;

    public GeoPoint(double longitude, double latitude) {
        this(longitude, latitude, null);
    }
}
