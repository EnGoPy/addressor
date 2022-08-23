package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.model.PhotonResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Component
@Slf4j
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

    @PostConstruct
    protected void handShakeWithPhoton(){

        int retryCount = 5;
        int backOff=400;
        int probeCounter = 0;
        boolean success;

        do {
            if(probeCounter != 0){
                try{
                    Thread.sleep(backOff);
                }catch (InterruptedException e){
                    log.warn("Thread interrupted during backOff");
                }
            }
            success = successHandShakeWithPhotonInternal();
            probeCounter++;
            if(success) break;

        }while(probeCounter <= retryCount);

        if(success){
            log.info("Successfully found photon on {}, attempts count {}", propertySettings.getSearchPhotonUrl(), probeCounter);
        }else{
            log.warn("Initial handshake with photon failed");
        }
    }

    public PhotonResponse getLocationByGeoCoords(double lat, double lon){
        LOGGER.debug("Use reversed geo-coding with photon");

        String url = "http://" +
                propertySettings.getSearchPhotonUrl() +
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
                                +"/api/?q=%s&lang=en",
                        location));
        if(propertySettings.getAutoSearchPhotonRequestLimit() != 0){
            url
                    .append("&limit=")
                    .append(propertySettings.getAutoSearchPhotonRequestLimit());
        }

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

    private boolean successHandShakeWithPhotonInternal(){
        String u = propertySettings.getSearchPhotonUrl().concat("/api?q");
        ResponseEntity<String> response;
                try{
                    response = restTemplate.getForEntity(u, String.class);
                    return HttpStatus.valueOf(response.getStatusCodeValue()).is2xxSuccessful();
                }catch (Exception e){
                    return false;
                }
    }
}
