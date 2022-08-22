package com.engobytes.addressor.service;

import com.engobytes.addressor.api.model.AutoFillResponse;
import com.engobytes.addressor.service.model.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationAddressService {

    GeoPoint getAddressFromGeoCords(double lng, double lat);

    List<AutoFillResponse> getLocationsSuggestionsByNamePart(String searchPhrase);
}
