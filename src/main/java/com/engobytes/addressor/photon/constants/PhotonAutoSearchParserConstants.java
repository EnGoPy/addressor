package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class PhotonAutoSearchParserConstants {

    public static HashSet<Pair<String, String>> ALLOWED_TAG_PAIRS = new HashSet<>();

    private static final HashSet<String> SHOP_VALUES = new HashSet<>(Arrays.asList( "alcohol","bakery","beauty", "books" ,"car", "car_parts", "car_repair",
            "chemist", "clothes", "convenience", "copyshop", "cosmetics", "department_store", "doityourself", "electronics",
            "florist", "greengrocer", "health_food", "mall", "seafood", "sports","stationery", "supermarket"));

    private static final HashSet<String> HIGHWAY_VALUES = new HashSet<>(Arrays.asList( "living_street", "pedestrian", "residential",
            "secondary", "service", "tertiary", "unclassified", "motorway"));

    private static final HashSet<String> BUILDING_VALUES =new HashSet<>(Arrays.asList("apartments", "manufacture", "residential", "yes", "train_station"));

    private static final HashSet<String> AMENITY_VALUES = new HashSet<>(Arrays.asList("atm", "bank", "bar", "bus_station",  "cafe", "childcare",
            "clinic", "doctors", "fast_food", "fuel", "hospital", "kindergarten", "language_school", "library",  "marketplace", "pharmacy",
            "place_of_worship", "post_office",  "prison", "pub", "restaurant", "school", "townhall", "university", "vehicle_inspection"));

    private static final HashSet<String> OFFICE_VALUES = new HashSet<>(Arrays.asList("company", "university", "government"));
    private static final HashSet<String> LEISURE_VALUES = new HashSet<>(Arrays.asList("park", "pitch","sports_centre"));
    private static final HashSet<String> TOURISM_VALUES = new HashSet<>(Arrays.asList("attraction", "hotel", "motel", "museum"));
    private static final HashSet<String> LAND_USE_VALUES = new HashSet<>(Arrays.asList("allotments", "cemetery", "commercial",
            "industrial", "military"));
    private static final HashSet<String> MAN_MADE_VALUES = new HashSet<>(Arrays.asList("tower"));
    private static final HashSet<String> WATERWAY_VALUES = new HashSet<>(Arrays.asList("canal", "dock"));
    private static final HashSet<String> PLACE_VALUES = new HashSet<>(Arrays.asList("suburb", "house"));
    private static final HashSet<String> HISTORIC_VALUES = new HashSet<>(Arrays.asList("memorial"));


    PhotonAutoSearchParserConstants(){
        initializeAllowedTags(ALLOWED_TAG_PAIRS);
    }


    protected void initializeAllowedTags(HashSet<Pair<String, String>> tagPairs) {
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

    protected void addKeyValues(HashSet<Pair<String, String>> tagPairs, String key, HashSet<String> values) {
        for (String value : values) {
            tagPairs.add(Pair.of(key, value));
        }
    }
}
