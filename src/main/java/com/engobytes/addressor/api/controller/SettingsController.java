package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AutosearchSettingsApi;
import com.engobytes.addressor.api.model.ReversedSettingsApi;
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


    @PostMapping(value = "/autosearch")
    public HttpStatus setAutosearchSettings(@RequestBody AutosearchSettingsApi settings){
        return HttpStatus.OK;
    }

    @GetMapping(value = "/autosearch")
    public AutosearchSettingsApi getAutosearchSettings(){
        return AutosearchSettingsApi.builder()
                .enableFiltering(true)
                .allowedCities("Gliwice,Poznań")
                .filteringLimit(3)
                .photonApiLimit(2)
                .useBoundary(false)
                .westBoundary(12)
                .northBoundary(10)
                .eastBoundary(23)
                .southBoundary(42)
                .build();
    }

    @PostMapping(value = "/reversed")
    public HttpStatus setReversedSettings(@RequestBody ReversedSettingsApi settings){
        return HttpStatus.OK;
    }

    @GetMapping(value = "/reversed")
    public ReversedSettingsApi getReversedSettings(){
        return ReversedSettingsApi.builder()
                .enableFiltering(true)
                .build();
    }

}
