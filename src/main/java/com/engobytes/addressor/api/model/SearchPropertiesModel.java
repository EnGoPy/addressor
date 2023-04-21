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

    private boolean reverseGeocodingFiltering;
    private boolean filterAutosearchWithAllowedTags;
    private int autoSearchPhotonRequestLimit;
    private int autoSearchResultLimit;
    private List<String> allowedCountryCodes;
    private List<String> includeCities;
    private Boolean useBoundaryBox;
    private Double westernSearchBoundary;
    private Double southSearchBoundary;
    private Double easternSearchBoundary;
    private Double northSearchBoundary;
}
