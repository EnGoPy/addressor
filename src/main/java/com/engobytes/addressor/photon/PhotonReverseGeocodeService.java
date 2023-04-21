package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.SearchProperties;
import com.engobytes.addressor.photon.model.PhotonResponse;
import com.engobytes.addressor.service.ReverseGeocodeService;
import com.engobytes.addressor.service.model.GeoPoint;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotonReverseGeocodeService implements ReverseGeocodeService {

    @Autowired
    SearchProperties propertySettings;
    @Autowired
    ConnectionPhotonAutoSearchGateway connectionPhotonAutoSearchGateway;

    @Override
    public GeoPoint getAddressFromGeoCoords(LatLng coords) {
        PhotonResponse foundLocation = connectionPhotonAutoSearchGateway.getLocationByGeoCoords(coords.lat, coords.lng);
        return PhotonValueParser.parseReverseGeoCodeResponse(coords, foundLocation, propertySettings);
    }
}
