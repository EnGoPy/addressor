package com.engobytes.addressor.service;

import com.engobytes.addressor.api.model.AutoFillResponse;
import com.engobytes.addressor.api.model.AutofillLocationDescription;
import com.engobytes.addressor.configuration.SearchProperties;
import com.engobytes.addressor.exception.AddressNotFoundException;
import com.engobytes.addressor.service.model.AutoFillSuggestion;
import com.engobytes.addressor.service.model.GeoPoint;
import com.engobytes.addressor.utils.WordParser;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationAddressServiceImpl implements LocationAddressService {

    @Autowired
    private AutoSearchService autoSearchService;
    @Autowired
    private ReverseGeocodeService reverseGeocodeService;
    @Autowired
    private SearchProperties searchProperties;

    @Override
    public GeoPoint getAddressFromGeoCords(double lng, double lat) {
        LatLng coordinates = new LatLng(lat, lng);

        GeoPoint foundLocationByCoords = reverseGeocodeService.getAddressFromGeoCoords(coordinates);
        String searchAddressDescription = foundLocationByCoords.getDescription();

        if (searchAddressDescription.isEmpty()) {
            throw new AddressNotFoundException("Address not found");
        }

        return new GeoPoint(lng, lat, searchAddressDescription);
    }

    @Override
    public List<AutoFillResponse> getLocationsSuggestionsByNamePart(String searchPhrase) {
        List<AutoFillSuggestion> osmSuggestions = autoSearchService.getPropositionsByNamePart(searchPhrase);
        return osmSuggestions.stream()
                .limit(searchProperties.getAutoSearchResultLimit())
                .map(suggestion -> {
                    AutofillLocationDescription autofillLocationDescription =
                            new AutofillLocationDescription(suggestion.getTitle(), suggestion.getAddress(), WordParser.findMatchingPairsInString(suggestion.getTitle(), searchPhrase));
                    return (AutoFillResponse.builder()
                            .description(autofillLocationDescription)
                            .latitude(suggestion.getLatitude())
                            .longitude(suggestion.getLongitude())
                            .build());
                })
                .collect(Collectors.toList());
    }
}
