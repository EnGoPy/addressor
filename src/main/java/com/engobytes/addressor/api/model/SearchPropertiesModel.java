package com.engobytes.addressor.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SearchPropertiesModel {

    private Boolean reverseGeocodingFiltering;
    private Boolean filterAutosearchWithAllowedTags;
    private Integer autoSearchPhotonRequestLimit;
    private Integer autoSearchResultLimit;
    private List<String> allowedCountryCodes;
    private List<String> allowedCities;
    private Boolean useBoundaryBox;
    private Double westernSearchBoundary;
    private Double southSearchBoundary;
    private Double easternSearchBoundary;
    private Double northSearchBoundary;
}
