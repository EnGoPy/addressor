package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AutosearchSettingsApi;
import com.engobytes.addressor.api.model.ReversedSettingsApi;
import com.engobytes.addressor.api.model.SearchPropertiesModel;
import com.engobytes.addressor.configuration.SearchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SearchProperties searchProperties;

    @PostMapping(value = "/autosearch")
    public HttpStatus setAutosearchSettings(@RequestBody AutosearchSettingsApi settings){
        return HttpStatus.OK;
    }

    @GetMapping(value = "/autosearch")
    public AutosearchSettingsApi getAutosearchSettings(){
        SearchPropertiesModel properties = searchProperties.getProperties();
        return AutosearchSettingsApi.builder()
                .enableFiltering(properties.isFilterAutosearchWithAllowedTags())
                .allowedCities(String.join(",", properties.getIncludeCities()))
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
