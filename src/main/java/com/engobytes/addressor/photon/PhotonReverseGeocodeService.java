package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.model.PhotonResponse;
import com.engobytes.addressor.service.ReverseGeocodeService;
import com.engobytes.addressor.service.model.GeoPoint;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PhotonReverseGeocodeService implements ReverseGeocodeService {

    @Autowired
    LocationSearchProperty propertySettings;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public GeoPoint getAddressFromGeoCoords(LatLng coords) {
        ConnectionPhotonAutoSearchGateway photonConnection  =
                new ConnectionPhotonAutoSearchGateway(propertySettings.getSearchPhotonUrl(), restTemplate, propertySettings);
        PhotonResponse foundLocation = photonConnection.getLocationByGeoCoords(coords.lat, coords.lng);
        return PhotonValueParser.parseReverseGeoCodeResponse(coords, foundLocation, propertySettings);
    }
}
