package com.engobytes.addressor.configuration;

import com.engobytes.addressor.api.model.SearchPropertiesModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@EndpointWebExtension(endpoint = InfoEndpoint.class)
@Getter
public class SearchProperties {


    @Value("${photon.ip}")
    private String photonIp;

    @Value("${photon.port}")
    private String photonPort;

    private boolean reverseGeocodingFiltering = true;

    private boolean filterAutosearchWithAllowedTags = true;

    @Value("${map.location.autosearch.filtering.photon.limit:0}") //0 value means we don't limit photon at all
    private int autoSearchPhotonRequestLimit;

    @Value("${map.location.autosearch.filtering.api.limit:8}")
    private int autoSearchResultLimit;

    @Value("#{'${map.location.autosearch.filtering.countries}'.empty?(new java.util.ArrayList()):'${map.location.autosearch.filtering.countries}'.split(',')}")
    private List<String> allowedCountryCodes;

    @Value("#{'${map.location.autosearch.filtering.cities}'.empty?(new java.util.ArrayList()):'${map.location.autosearch.filtering.cities}'.split(',')}")
    private List<String> includeCities = new ArrayList<>();

    @Value("#{new Boolean('${map.location.autosearch.search.useBoundary:false}')}")
    private Boolean useBoundaryBox;

    @Value("${map.location.autosearch.search.W_boundary}")
    private Double westernSearchBoundary;

    @Value("${map.location.autosearch.search.S_boundary}")
    private Double southSearchBoundary;

    @Value("${map.location.autosearch.search.E_boundary}")
    private Double easternSearchBoundary;

    @Value("${map.location.autosearch.search.N_boundary}")
    private Double northSearchBoundary;

    @Getter(AccessLevel.NONE)
    private List<Double> boundaryBox = new ArrayList<>();

    @PostConstruct
    private void setUp(){
        log.info("S {}", southSearchBoundary);
        log.info("N {}", northSearchBoundary);
        log.info("E {}", easternSearchBoundary);
        log.info("W {}", westernSearchBoundary);
        initializeBbox();
    }

    public String getBoundaryForPhotonUrl(){
        String bboxAsString = "";
        for(int i=1; i <= boundaryBox.size(); i++){
            bboxAsString = bboxAsString.concat(boundaryBox.get(i-1).toString());
            if(i < getBoundaryBox().size()){
                bboxAsString = bboxAsString.concat(",");
            }
        }
        return bboxAsString;
    }

    public void setReverseGeocodingFiltering(boolean reverseGeocodingFiltering) {
        this.reverseGeocodingFiltering = reverseGeocodingFiltering;
    }

    public void setFilterAutosearchWithAllowedTags(boolean filterAutosearchWithAllowedTags) {
        this.filterAutosearchWithAllowedTags = filterAutosearchWithAllowedTags;
    }

    public SearchPropertiesModel getProperties(){
        return SearchPropertiesModel.builder()
                .reverseGeocodingFiltering(reverseGeocodingFiltering)
                .filterAutosearchWithAllowedTags(filterAutosearchWithAllowedTags)
                .autoSearchPhotonRequestLimit(autoSearchPhotonRequestLimit)
                .autoSearchResultLimit(autoSearchResultLimit)
                .allowedCountryCodes(allowedCountryCodes)
                .includeCities(includeCities)
                .useBoundaryBox(useBoundaryBox)
                .westernSearchBoundary(westernSearchBoundary)
                .southSearchBoundary(southSearchBoundary)
                .easternSearchBoundary(easternSearchBoundary)
                .northSearchBoundary(northSearchBoundary)
                .build();
    }

    @ReadOperation
    public WebEndpointResponse<Map> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("photonIp", getPhotonIp());
        info.put("photonPort", getPhotonPort());
        info.put("reverseGeocodingFiltering", isReverseGeocodingFiltering());
        info.put("filterAutosearchWithAllowedTags", isFilterAutosearchWithAllowedTags());
        info.put("autoSearchResultLimit", getAutoSearchResultLimit());
        info.put("allowedCountryCodes", getAllowedCountryCodes());
        info.put("includeCities", getIncludeCities());
        info.put("useBoundaryBox", getUseBoundaryBox());
        info.put("westernSearchBoundary", getWesternSearchBoundary());
        info.put("southSearchBoundary", getSouthSearchBoundary());
        info.put("easternSearchBoundary", getEasternSearchBoundary());
        info.put("northSearchBoundary", getNorthSearchBoundary());
        info.put("boundaryBox", getBoundaryBox());
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("appProperties", info);

        return new WebEndpointResponse<>(wrapper);
    }

    private List<Double> getBoundaryBox(){
        return boundaryBox;
    }

    private void initializeBbox(){
        if(boundaryBox.isEmpty()){
            boundaryBox.add(westernSearchBoundary);
            boundaryBox.add(northSearchBoundary);
            boundaryBox.add(easternSearchBoundary);
            boundaryBox.add(southSearchBoundary);
        }
    }
}
