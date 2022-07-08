package com.engobytes.addressor.service;

import com.engobytes.addressor.service.model.GeoPoint;
import com.google.maps.model.LatLng;

public interface ReverseGeocodeService {

    GeoPoint getAddressFromGeoCoords(LatLng coords);

}
