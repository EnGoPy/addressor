package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AutosearchSettingsApi;
import com.engobytes.addressor.api.model.ReversedSettingsApi;
import com.engobytes.addressor.configuration.SearchProperties;
import com.engobytes.addressor.mapper.AutosearchSettingsMapper;
import com.engobytes.addressor.mapper.ReversedSettingsMapper;
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
        searchProperties.setAutosearchSettings(AutosearchSettingsMapper.fromApiToModel(settings));
        return HttpStatus.OK;
    }

    @GetMapping(value = "/autosearch")
    public AutosearchSettingsApi getAutosearchSettings(){
        return AutosearchSettingsMapper.fromModelToApi(searchProperties.getProperties());
    }

    @PostMapping(value = "/reversed")
    public HttpStatus setReversedSettings(@RequestBody ReversedSettingsApi settings){
        searchProperties.setReverseSettings(settings.isEnableFiltering());
        return HttpStatus.OK;
    }

    @GetMapping(value = "/reversed")
    public ReversedSettingsApi getReversedSettings(){
        return ReversedSettingsMapper.fromModelToApi(searchProperties.getProperties());
    }

}
