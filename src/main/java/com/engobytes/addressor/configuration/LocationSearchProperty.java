package com.engobytes.addressor.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class LocationSearchProperty {

    @Value("${photon.url}")
    private String searchPhotonUrl;

    @Value("#{new Boolean('${map.location.reverseGeocode.filtering.enable:true}')}")
    private Boolean reverseGeocodingFiltering;

    @Value("#{new Boolean('${map.location.autosearch.filtering.enable:true}')}")
    private Boolean filterAutosearchWithAllowedTags;

    @Value("${map.location.autosearch.filtering.limit:8}")
    private int autoSearchResultLimit;

    @Value("#{'${map.location.autosearch.filtering.countries}'.split(',\\s*')}")
    private List<String> allowedCountryCodes;

    @Value("#{'${map.location.autosearch.filtering.cities}'.split(',\\s*')}")
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

    public String getBoundaryForPhotonUrl(){
        String bboxAsString = "";
        for(int i=0; i < getBoundaryBox().size(); i++){
            bboxAsString = bboxAsString.concat(getBoundaryBox().get(i).toString());
            if(i < getBoundaryBox().size()){
                bboxAsString = bboxAsString.concat(",");
            }
        }
        return bboxAsString;
    }

    private List<Double> getBoundaryBox(){
        if(boundaryBox.isEmpty()){
            boundaryBox.add(westernSearchBoundary);
            boundaryBox.add(southSearchBoundary);
            boundaryBox.add(easternSearchBoundary);
            boundaryBox.add(northSearchBoundary);
        }
        return boundaryBox;
    }
}
