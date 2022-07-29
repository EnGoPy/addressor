package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.model.PhotonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;

@Component
public class ConnectionPhotonAutoSearchGateway {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPhotonAutoSearchGateway.class);
    private final RestTemplate restTemplate;
    private final LocationSearchProperty propertySettings;

    public ConnectionPhotonAutoSearchGateway(
            @Autowired  RestTemplate restTemplate,
            @Autowired @NotNull LocationSearchProperty propertySettings) {
        this.restTemplate = restTemplate;
        this.propertySettings = propertySettings;
    }

    public PhotonResponse getLocationByGeoCoords(double lat, double lon){
        LOGGER.debug("Use reversed geo-coding with photon");

        String url = "http://" + propertySettings.getSearchPhotonUrl() +
                "/reverse?lon=" +
                lon +
                "&lat=" +
                lat;

        return getAutoSearchResponse(url);
    }

    public PhotonResponse getPropositionsByName(String location) {
        LOGGER.debug("Use autosearch with photon");
        StringBuilder url = new StringBuilder(
                String.format("http://"
                                +propertySettings.getSearchPhotonUrl()
                                +"/api/?q=%s&lang=en&limit=%s",
                        location, propertySettings.getAutoSearchResultLimit()));

        if(propertySettings.getUseBoundaryBox()){
            url
                    .append("%bbox=")
                    .append(propertySettings.getBoundaryForPhotonUrl());
        }
        return getAutoSearchResponse(url.toString());
    }


    private PhotonResponse getAutoSearchResponse(String url) {
        ResponseEntity<PhotonResponse> response = restTemplate.getForEntity(url, PhotonResponse.class);
        PhotonResponse propositions = response.getBody();
        if (propositions != null && propositions.getFeatures().size() != 0) {
            return propositions;
        } else {
            return new PhotonResponse();
        }
    }
}
