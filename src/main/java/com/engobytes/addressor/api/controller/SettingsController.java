package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AutosearchSettingsApi;
import com.engobytes.addressor.api.model.ReversedSettingsApi;
import com.engobytes.addressor.api.model.SearchPropertiesModel;
import com.engobytes.addressor.configuration.SearchProperties;
import com.engobytes.addressor.utils.WordParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SearchProperties searchProperties;

    @PostMapping(value = "/autosearch")
    public HttpStatus setAutosearchSettings(@RequestBody AutosearchSettingsApi settings){
        searchProperties.setAutosearchSettings(
                SearchPropertiesModel.builder()
                        .filterAutosearchWithAllowedTags(settings.isEnableFiltering())
                        .allowedCities(Arrays.stream(settings.getAllowedCities().split(","))
                                .map(cityName -> WordParser.eraseFinishingStrings(cityName, " "))
                                .collect(Collectors.toList()))
                        .allowedCountryCodes(Arrays.stream(settings.getAllowedCountryCodes().split(","))
                                .map(countryCode -> WordParser.eraseFinishingStrings(countryCode, " "))
                                .collect(Collectors.toList()))
                        .autoSearchResultLimit(settings.getFilteringLimit())
                        .autoSearchPhotonRequestLimit(settings.getPhotonApiLimit())
                        .useBoundaryBox(settings.isUseBoundary())
                        .westernSearchBoundary(settings.getWestBoundary())
                        .northSearchBoundary(settings.getNorthBoundary())
                        .easternSearchBoundary(settings.getEastBoundary())
                        .southSearchBoundary(settings.getSouthBoundary())
                        .build()
        );
        return HttpStatus.OK;
    }

    @GetMapping(value = "/autosearch")
    public AutosearchSettingsApi getAutosearchSettings(){
        SearchPropertiesModel properties = searchProperties.getProperties();
        return AutosearchSettingsApi.builder()
                .enableFiltering(properties.isFilterAutosearchWithAllowedTags())
                .allowedCities(String.join(",", properties.getAllowedCities()))
                .allowedCountryCodes(String.join(",", properties.getAllowedCountryCodes()))
                .filteringLimit(properties.getAutoSearchResultLimit())
                .photonApiLimit(properties.getAutoSearchPhotonRequestLimit())
                .useBoundary(properties.getUseBoundaryBox())
                .westBoundary(properties.getWesternSearchBoundary())
                .northBoundary(properties.getNorthSearchBoundary())
                .eastBoundary(properties.getEasternSearchBoundary())
                .southBoundary(properties.getSouthSearchBoundary())
                .build();
    }

    @PostMapping(value = "/reversed")
    public HttpStatus setReversedSettings(@RequestBody ReversedSettingsApi settings){
        searchProperties.setReverseSettings(
                SearchPropertiesModel.builder()
                        .reverseGeocodingFiltering(settings.isEnableFiltering())
                        .build()
        );
        return HttpStatus.OK;
    }

    @GetMapping(value = "/reversed")
    public ReversedSettingsApi getReversedSettings(){
        SearchPropertiesModel properties = searchProperties.getProperties();
        return ReversedSettingsApi.builder()
                .enableFiltering(properties.isReverseGeocodingFiltering())
                .build();
    }

}
