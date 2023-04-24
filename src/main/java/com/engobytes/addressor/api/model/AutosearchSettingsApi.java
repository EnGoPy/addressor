package com.engobytes.addressor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AutosearchSettingsApi {

    private boolean enableFiltering;
    private String allowedCities;
    private String allowedCountryCodes;
    private int filteringLimit;
    private int photonApiLimit;
    private boolean useBoundary;
    private double westBoundary;
    private double northBoundary;
    private double eastBoundary;
    private double southBoundary;

}