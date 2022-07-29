package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.model.PhotonResponse;
import com.engobytes.addressor.service.AutoSearchService;
import com.engobytes.addressor.service.model.AutoFillSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotonLocationSearchService implements AutoSearchService {

    @Autowired
    LocationSearchProperty locationSearchProperty;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<AutoFillSuggestion> getPropositionsByNamePart(String location) {
        ConnectionPhotonAutoSearchGateway photonConnection  =
                new ConnectionPhotonAutoSearchGateway(locationSearchProperty.getSearchPhotonUrl(), restTemplate, locationSearchProperty);
        PhotonResponse photonAutosearchResponse = photonConnection.getPropositionsByName(location);
        List<AutoFillSuggestion> autoFillSuggestions = PhotonValueParser.parseAutoSearchResponse(photonAutosearchResponse, locationSearchProperty);
        return filterOutTitleDuplicates(autoFillSuggestions);
    }

    private List<AutoFillSuggestion> filterOutTitleDuplicates(List<AutoFillSuggestion> rawSuggestions){
        List<AutoFillSuggestion> uniqueTitleLocations = new ArrayList<>();
        for(AutoFillSuggestion suggestion : rawSuggestions){
            if(!uniqueTitleLocations.contains(suggestion)){
                uniqueTitleLocations.add(suggestion);
            }
        }
        return uniqueTitleLocations;
    }
}
