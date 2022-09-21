package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.AddressResponse;
import com.engobytes.addressor.api.model.AutoFillResponse;
import com.engobytes.addressor.api.model.AutoSearchResponse;
import com.engobytes.addressor.mapper.AddressResponseMapper;
import com.engobytes.addressor.service.LocationAddressService;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
@Validated
public class LocationController {

    @Autowired
    private LocationAddressService locationAddressService;


    @GetMapping(value = "/getAddress")
    public AddressResponse getLocationAddressFromGeographicalCoordinates(@Range(min= - 180, max = 180) @RequestParam(value = "lng") double longitude,
                                                                         @Range(min= - 90, max = 90) @RequestParam(value = "lat") double latitude) {
        return AddressResponseMapper.mapFromGeoPoint(locationAddressService.getAddressFromGeoCords(longitude, latitude));
    }


    @GetMapping(value = "/autoFill/{address}")
    public AutoSearchResponse locationAutoSearch(@NotBlank @Length(min=1, max=50) @PathVariable(name = "address") String address) {
        List<AutoFillResponse> locationsSuggestionsByNamePart = locationAddressService.getLocationsSuggestionsByNamePart(address);
        return new AutoSearchResponse(locationsSuggestionsByNamePart);
    }


}
