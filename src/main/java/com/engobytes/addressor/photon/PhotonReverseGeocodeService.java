package com.engobytes.addressor.photon;

import com.engobytes.addressor.service.ReverseGeocodeService;
import com.engobytes.addressor.service.model.GeoPoint;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Service;

@Service
public class PhotonReverseGeocodeService implements ReverseGeocodeService {

    @Override
    public GeoPoint getAddressFromGeoCoords(LatLng coords) {
        return null;
    }
}
