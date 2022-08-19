package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PhotonAutoSearchParserConstants {

    public static Set<Pair<String, String>> ALLOWED_TAG_PAIRS = new HashSet<>();

    protected static final HashSet<String> SHOP_VALUES = new HashSet<>(Arrays.asList( "alcohol","bakery","beauty", "books" ,"car", "car_parts", "car_repair",
            "chemist", "clothes", "convenience", "copyshop", "cosmetics", "department_store", "doityourself", "electronics",
            "florist", "greengrocer", "health_food", "mall", "seafood", "sports","stationery", "supermarket"));

    protected static final HashSet<String> HIGHWAY_VALUES = new HashSet<>(Arrays.asList( "living_street", "pedestrian", "residential",
            "secondary", "service", "tertiary", "unclassified", "motorway"));

    protected static final HashSet<String> BUILDING_VALUES =new HashSet<>(Arrays.asList("apartments", "manufacture", "residential", "yes", "train_station"));

    protected static final HashSet<String> AMENITY_VALUES = new HashSet<>(Arrays.asList("atm", "bank", "bar", "bus_station",  "cafe", "childcare",
            "clinic", "doctors", "fast_food", "fuel", "hospital", "kindergarten", "language_school", "library",  "marketplace", "pharmacy",
            "place_of_worship", "post_office",  "prison", "pub", "restaurant", "school", "townhall", "university", "vehicle_inspection"));

    protected static final HashSet<String> OFFICE_VALUES = new HashSet<>(Arrays.asList("company", "university", "government"));
    protected static final HashSet<String> LEISURE_VALUES = new HashSet<>(Arrays.asList("park", "pitch","sports_centre"));
    protected static final HashSet<String> TOURISM_VALUES = new HashSet<>(Arrays.asList("attraction", "hotel", "motel", "museum"));
    protected static final HashSet<String> LAND_USE_VALUES = new HashSet<>(Arrays.asList("allotments", "cemetery", "commercial",
            "industrial", "military"));
    protected static final HashSet<String> MAN_MADE_VALUES = new HashSet<>(Arrays.asList("tower"));
    protected static final HashSet<String> WATERWAY_VALUES = new HashSet<>(Arrays.asList("canal", "dock"));
    protected static final HashSet<String> PLACE_VALUES = new HashSet<>(Arrays.asList("suburb", "house"));
    protected static final HashSet<String> HISTORIC_VALUES = new HashSet<>(Arrays.asList("memorial"));

    PhotonAutoSearchParserConstants(){
        initializeAllowedTags(ALLOWED_TAG_PAIRS);
    }

    protected void initializeAllowedTags(Set<Pair<String, String>> allowedTagContainer) {
        FilterTagUtils.addKeyValues(allowedTagContainer,"shop" , SHOP_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "highway", HIGHWAY_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "building", BUILDING_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "amenity", AMENITY_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "office", OFFICE_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "leisure", LEISURE_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "tourism", TOURISM_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "landuse", LAND_USE_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "man_made", MAN_MADE_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "waterway", WATERWAY_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "place", PLACE_VALUES);
        FilterTagUtils.addKeyValues(allowedTagContainer, "historic", HISTORIC_VALUES);
    }

    public void addAdditionalTag(Map<String, String> tag){
        FilterTagUtils.addCustomTags(ALLOWED_TAG_PAIRS, tag);
    }

    public void removeTag(Map<String, String> tag){
        FilterTagUtils.removeCustomTags(ALLOWED_TAG_PAIRS, tag);
    }

}
