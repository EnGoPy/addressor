package com.engobytes.addressor.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AutosearchSettingsApi {

    private boolean enableFiltering;
    private String allowedCities;
    private int filteringLimit;
    private int photonApiLimit;
    private boolean useBoundary;
    private double westBoundary;
    private double northBoundary;
    private double eastBoundary;
    private double southBoundary;

}