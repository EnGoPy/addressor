package com.engobytes.addressor.mapper;

import com.engobytes.addressor.api.model.AddressResponse;
import com.engobytes.addressor.service.model.GeoPoint;

public class AddressResponseMapper {

    public static AddressResponse mapFromGeoPoint(GeoPoint geoPoint){
        if(geoPoint != null){
            return new AddressResponse(geoPoint.getDescription());
        }
        return null;
    }
}
