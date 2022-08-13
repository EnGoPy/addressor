package com.engobytes.addressor.photon;

import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.photon.constants.PhotonAutoSearchParserConstants;
import com.engobytes.addressor.photon.constants.PhotonReverseGeoCodingParserConstant;
import com.engobytes.addressor.photon.model.PhotonLocation;
import com.engobytes.addressor.photon.model.PhotonResponse;
import com.engobytes.addressor.service.model.AutoFillSuggestion;
import com.engobytes.addressor.service.model.GeoPoint;
import com.engobytes.addressor.service.model.Pair;
import com.engobytes.addressor.utils.SufixEnum;
import com.engobytes.addressor.utils.WordParser;
import com.google.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PhotonValueParser {

    public static GeoPoint parseReverseGeoCodeResponse(LatLng coordinates, PhotonResponse autoSearchResponse,
                                                       LocationSearchProperty properties) {
        GeoPoint foundProposition = new GeoPoint(coordinates.lng, coordinates.lat, "");
        if (!autoSearchResponse.getFeatures().isEmpty()) {
            Optional<String> description = autoSearchResponse.getFeatures()
                    .stream()
                    .filter(location -> properties.getAllowedCountryCodes().contains(location.getProperties().getCountrycode()))
                    .filter(location -> {
                        if (properties.getReverseGeocodingFiltering()) {
                            String key = location.getProperties().getOsm_key();
                            String value = location.getProperties().getOsm_value();
                            return PhotonReverseGeoCodingParserConstant.REVERSED_GC_TAG_PARIS.contains(Pair.of(key, value));
                        } else {
                            return true;
                        }
                    })
                    .map(PhotonValueParser::parseReversedGeocodingDescription)
                    .findFirst();
            foundProposition.setDescription(description.orElse(""));
        }
        return foundProposition;
    }

    public static List<AutoFillSuggestion> parseAutoSearchResponse(PhotonResponse autoSearchResponse,
                                                                   LocationSearchProperty properties) {
        List<AutoFillSuggestion> foundPropositions = new ArrayList<>();
        if (!autoSearchResponse.getFeatures().isEmpty()) {
            foundPropositions = autoSearchResponse.getFeatures()
                    .stream()
                    .filter(location -> properties.getAllowedCountryCodes().contains(location.getProperties().getCountrycode()))
                    .filter(location -> {
                        if (properties.getFilterAutosearchWithAllowedTags()) {
                            String key = location.getProperties().getOsm_key();
                            String value = location.getProperties().getOsm_value();

                            boolean allowedCity =
                                    properties.getIncludeCities().isEmpty() || properties.getIncludeCities().contains(location.getProperties().getCity());
                            boolean allowedTagPair = PhotonAutoSearchParserConstants.ALLOWED_TAG_PAIRS.contains(Pair.of(key, value));

                            return allowedCity && allowedTagPair;
                        } else {
                            return true;
                        }
                    })
                    .map(PhotonValueParser::parseAutoSearchDescription)
                    .collect(Collectors.toList());
        }
        return foundPropositions;
    }

    public static String parseReversedGeocodingDescription(PhotonLocation location) {
        AutoFillSuggestion parsedLocation = parseAutoSearchDescription(location);
        return parsedLocation.getTitle() + " " + parsedLocation.getAddress();
    }

    public static AutoFillSuggestion parseAutoSearchDescription(PhotonLocation location) {
        String title = "";
        String address = "";
        switch (location.getProperties().getOsm_key()) {
            case "building":
                title = parseBuildingTitle(location);
                address = parseBuildingAddress(location);
                break;
            case "highway":
                title = parseHighwayTitle(location);
                address = parseHighwayAddress(location);
                break;
            default:
                title = parseAmenityTitle(location);
                address = parseAmenityAddress(location);
        }
        AutoFillSuggestion response = new AutoFillSuggestion(
                location.getGeometry().getLat(),
                location.getGeometry().getLon(),
                WordParser.eraseFinishingStrings(title, ","),
                WordParser.eraseFinishingStrings(address, ","));
        return response;
    }

    protected static String parseAmenityTitle(PhotonLocation location) {
        return WordParser.getValueOrNothing(location.getProperties().getName(), SufixEnum.NOTHING);
    }

    protected static String parseHighwayTitle(PhotonLocation location) {
        return WordParser.getValueOrNothing(location.getProperties().getName(), SufixEnum.NOTHING);
    }

    protected static String parseBuildingTitle(PhotonLocation location) {
        StringBuilder sb = new StringBuilder();
        sb.append(WordParser.getValueOrNothing(location.getProperties().getName(), SufixEnum.NOTHING));
        return sb.toString();
    }

    protected static String parseHighwayAddress(PhotonLocation location) {
        StringBuilder sb = new StringBuilder();
        sb.append(WordParser.getValueOrNothing(location.getProperties().getDistrict(), SufixEnum.NOTHING));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getCity(), SufixEnum.COMA));
        return sb.toString();
    }

    protected static String parseAmenityAddress(PhotonLocation location) {
        StringBuilder sb = new StringBuilder();
        sb.append(WordParser.getValueOrNothing(location.getProperties().getStreet(), SufixEnum.NOTHING));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getHousenumber(), SufixEnum.SPACEBAR));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getDistrict(), SufixEnum.COMA));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getCity(), SufixEnum.COMA));
        return sb.toString();
    }

    protected static String parseBuildingAddress(PhotonLocation location) {
        StringBuilder sb = new StringBuilder();
        sb.append(WordParser.getValueOrNothing(location.getProperties().getStreet(), SufixEnum.NOTHING));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getHousenumber(), SufixEnum.SPACEBAR));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getDistrict(), SufixEnum.COMA));
        sb.append(WordParser.getValueOrNothing(location.getProperties().getCity(), SufixEnum.COMA));
        return sb.toString();
    }

}