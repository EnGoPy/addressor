package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.model.PhotonResponse;
import com.engobytes.addressor.service.AutoSearchService;
import com.engobytes.addressor.service.model.AutoFillSuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotonLocationSearchService implements AutoSearchService {

    @Autowired
    LocationSearchProperty locationSearchProperty;
    @Autowired
    ConnectionPhotonAutoSearchGateway connectionPhotonAutoSearchGateway;

    @Override
    public List<AutoFillSuggestion> getPropositionsByNamePart(String location) {
        PhotonResponse photonAutosearchResponse = connectionPhotonAutoSearchGateway.getPropositionsByName(location);
        List<AutoFillSuggestion> autoFillSuggestions = PhotonValueParser.parseAutoSearchResponse(photonAutosearchResponse, locationSearchProperty);
        return filterOutTitleDuplicates(autoFillSuggestions);
    }

    private List<AutoFillSuggestion> filterOutTitleDuplicates(List<AutoFillSuggestion> rawSuggestions){
        return rawSuggestions
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
