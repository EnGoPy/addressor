package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PhotonAutoSearchParserConstants {
    public static List<Pair<String, String>> ALLOWED_TAG_PAIRS;

    private static final List<String> SHOP_VALUES = Arrays.asList( "alcohol","bakery","beauty", "books" ,"car", "car_parts", "car_repair",
            "chemist", "clothes", "convenience", "copyshop", "cosmetics", "department_store", "doityourself", "electronics",
            "florist", "greengrocer", "health_food", "mall", "seafood", "sports","stationery", "supermarket");

    private static final List<String> HIGHWAY_VALUES = Arrays.asList( "living_street", "pedestrian", "residential",
            "secondary", "service", "tertiary", "unclassified", "motorway");

    private static final List<String> BUILDING_VALUES = Arrays.asList("apartments", "manufacture", "residential", "yes", "train_station");

    private static final List<String> AMENITY_VALUES = Arrays.asList("atm", "bank", "bar", "bus_station",  "cafe", "childcare",
            "clinic", "doctors", "fast_food", "fuel", "hospital", "kindergarten", "language_school", "library",  "marketplace", "pharmacy",
            "place_of_worship", "post_office",  "prison", "pub", "restaurant", "school", "townhall", "university", "vehicle_inspection");

    private static final List<String> OFFICE_VALUES = Arrays.asList("company", "university", "government");
    private static final List<String> LEISURE_VALUES = Arrays.asList("park", "pitch","sports_centre");
    private static final List<String> TOURISM_VALUES = Arrays.asList("attraction", "hotel", "motel", "museum");
    private static final List<String> LAND_USE_VALUES = Arrays.asList("allotments", "cemetery", "commercial",
            "industrial", "military");
    private static final List<String> MAN_MADE_VALUES = Arrays.asList("tower");
    private static final List<String> WATERWAY_VALUES = Arrays.asList("canal", "dock");
    private static final List<String> PLACE_VALUES = Arrays.asList("suburb", "house");
    private static final List<String> HISTORIC_VALUES = Arrays.asList("memorial");

    PhotonAutoSearchParserConstants(){
        ALLOWED_TAG_PAIRS = new ArrayList<>();
        initializeAllowedTags(ALLOWED_TAG_PAIRS);
    }

    protected void initializeAllowedTags(List<Pair<String, String>> tagPairs) {
        addKeyValues(tagPairs,"shop" , SHOP_VALUES);
        addKeyValues(tagPairs, "highway", HIGHWAY_VALUES);
        addKeyValues(tagPairs, "building", BUILDING_VALUES);
        addKeyValues(tagPairs, "amenity", AMENITY_VALUES);
        addKeyValues(tagPairs, "office", OFFICE_VALUES);
        addKeyValues(tagPairs, "leisure", LEISURE_VALUES);
        addKeyValues(tagPairs, "tourism", TOURISM_VALUES);
        addKeyValues(tagPairs, "landuse", LAND_USE_VALUES);
        addKeyValues(tagPairs, "man_made", MAN_MADE_VALUES);
        addKeyValues(tagPairs, "waterway", WATERWAY_VALUES);
        addKeyValues(tagPairs, "place", PLACE_VALUES);
        addKeyValues(tagPairs, "historic", HISTORIC_VALUES);
    }

    protected void addKeyValues(List<Pair<String, String>> tagPairs, String key, List<String> values) {
        for (String value : values) {
            tagPairs.add(Pair.of(key, value));
        }
    }
}
