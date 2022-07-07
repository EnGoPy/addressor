package com.engobytes.addressor.mapper;

import com.engobytes.addressor.api.model.AddressRequestApi;
import com.engobytes.addressor.service.model.GeoPoint;

public class GeoPointMapper {

    public static GeoPoint mapFromAddressRequestApi(AddressRequestApi requestApi){
        if(requestApi != null){
            return new GeoPoint(requestApi.getLongitude(), requestApi.getLatitude());
        }
        return null;
    }

}
