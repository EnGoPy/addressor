package com.engobytes.addressor.mapper;

import com.engobytes.addressor.api.model.AutosearchSettingsApi;
import com.engobytes.addressor.api.model.SearchPropertiesModel;
import com.engobytes.addressor.utils.WordParser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AutosearchSettingsMapper {

    public static AutosearchSettingsApi fromModelToApi(SearchPropertiesModel model){
        if(model != null){
            return new AutosearchSettingsApi(
                    model.getReverseGeocodingFiltering(),
                    String.join(",", model.getAllowedCities()),
                    String.join(",", model.getAllowedCountryCodes()),
                    model.getAutoSearchResultLimit(),
                    model.getAutoSearchPhotonRequestLimit(),
                    model.getUseBoundaryBox(),
                    model.getWesternSearchBoundary(),
                    model.getNorthSearchBoundary(),
                    model.getEasternSearchBoundary(),
                    model.getSouthSearchBoundary()
            );
        }
        return null;
    }

    public static SearchPropertiesModel fromApiToModel(AutosearchSettingsApi api){
        if(api != null){
            return new SearchPropertiesModel(
                    null,
                    api.isEnableFiltering(),
                    api.getPhotonApiLimit(),
                    api.getFilteringLimit(),
                    Arrays.stream(api.getAllowedCountryCodes().split(","))
                            .map(countryCode -> WordParser.eraseFinishingStrings(countryCode, " "))
                            .collect(Collectors.toList()),
                    Arrays.stream(api.getAllowedCities().split(","))
                            .map(cityName -> WordParser.eraseFinishingStrings(cityName, " "))
                            .collect(Collectors.toList()),
                    api.isUseBoundary(),
                    api.getWestBoundary(),
                    api.getSouthBoundary(),
                    api.getEastBoundary(),
                    api.getNorthBoundary()
            );
        }
        return null;
    }
}
