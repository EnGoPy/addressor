package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AddressResponse;
import com.engobytes.addressor.api.model.AutoFillResponse;
import com.engobytes.addressor.api.model.AutoSearchResponse;
import com.engobytes.addressor.mapper.AddressResponseMapper;
import com.engobytes.addressor.service.LocationAddressService;
import com.engobytes.addressor.service.model.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/1/")
public class LocationController {

    @Autowired
    private LocationAddressService locationAddressService;


    @GetMapping(value = "/getAddress")
    public AddressResponse getLocationAddressFromGeographicalCoordinates(@RequestParam(value = "lng") double longitude,
                                                                         @RequestParam(value = "lat") double latitude) {
        GeoPoint geoPoint = new GeoPoint(longitude, latitude);
        return AddressResponseMapper.mapFromGeoPoint(locationAddressService.getAddressFromGeoCords(geoPoint));
    }


    @GetMapping(value = "/autoFill/{address}")
    public AutoSearchResponse locationAutoSearch(@PathVariable(name = "address") String address) {
        List<AutoFillResponse> locationsSuggestionsByNamePart = locationAddressService.getLocationsSuggestionsByNamePart(address);
        return new AutoSearchResponse(locationsSuggestionsByNamePart);
    }


}
