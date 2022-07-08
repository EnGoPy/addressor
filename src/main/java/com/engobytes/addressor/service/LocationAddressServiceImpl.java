package com.engobytes.addressor.service;

import com.engobytes.addressor.api.model.AutoFillResponse;
import com.engobytes.addressor.api.model.AutofillLocationDescription;
import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.exception.AddressNotFoundException;
import com.engobytes.addressor.photon.AutoSearchService;
import com.engobytes.addressor.service.model.AutoFillSuggestion;
import com.engobytes.addressor.service.model.GeoPoint;
import com.engobytes.addressor.utils.WordParser;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LocationAddressServiceImpl implements LocationAddressService{

    @Autowired
    private AutoSearchService autoSearchService;
    @Autowired
    private ReverseGeocodeService reverseGeocodeService;
    @Autowired
    private LocationSearchProperty locationSearchProperty;

    @Override
    public GeoPoint getAddressFromGeoCords(GeoPoint geoPoint) {
            LatLng coordinates = new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude());

            GeoPoint foundLocationByCoords = reverseGeocodeService.getAddressFromGeoCoords(coordinates);
            String searchAddressDescription = foundLocationByCoords.getDescription();

            if (searchAddressDescription.isEmpty()){
                throw new AddressNotFoundException("Address not found.");
            }

            geoPoint.setDescription(searchAddressDescription);

            return geoPoint;
    }

    @Override
    public List<AutoFillResponse> getLocationsSuggestionsByNamePart(String searchPhrase) {
        AtomicReference<String> filteredPhrase = new AtomicReference(WordParser.eraseFinishingStrings(searchPhrase, "Gliwice"));
        List<AutoFillSuggestion> osmSuggestions = autoSearchService.getPropositionsByNamePart(searchPhrase);
        AtomicReference<List<AutoFillResponse>> mat = new AtomicReference<>(new ArrayList<>());
        osmSuggestions.stream()
                .limit(locationSearchProperty.getAutoSearchResultLimit())
                .forEach(suggestion -> {
                    AutofillLocationDescription autofillLocationDescription =
                            new AutofillLocationDescription(suggestion.getTitle(), suggestion.getAddress(), WordParser.findMatchingPairsInString(suggestion.getTitle(), filteredPhrase.get()));
                    mat.get().add(AutoFillResponse.builder()
                            .description(autofillLocationDescription)
                            .latitude(suggestion.getLatitude())
                            .longitude(suggestion.getLongitude())
                            .build());
                });

        return mat.get();
    }
}
