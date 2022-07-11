package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

public class PhotonReverseGeoCodingParserConstant extends PhotonAutoSearchParserConstants{

    private static final HashSet<String> ADDITIONAL_AMENITY_VALUES = new HashSet<>(Arrays.asList( "bench", "bicycle_parking", "charging_station", "parking", "police", "vending_machine", "veterinary"));
    private static final HashSet<String> ADDITIONAL_HIGHWAY_VALUES = new HashSet<>(Arrays.asList("bus_stop", "platform", "primary",  "proposed", "tertiary"));
    private static final HashSet<String> ADDITIONAL_BUILDING_VALUES = new HashSet<>(Arrays.asList("commercial", "dormitory", "kindergarten", "industrial", "office", "university", "retail"));
    private static final HashSet<String> ADDITIONAL_PLACE_VALUES = new HashSet<>(Arrays.asList("city", "house"));
    private static final HashSet<String> ADDITIONAL_RAILWAY_VALUES = new HashSet<>(Arrays.asList("platform"));
    private static final HashSet<String> ADDITIONAL_LAND_USE_VALUES = new HashSet<>(Arrays.asList("construction", "residential"));
    private static final HashSet<String> ADDITIONAL_BOUNDARY_VALUES = new HashSet<>(Arrays.asList("administrative"));
    private static final HashSet<String> ADDITIONAL_SHOP_VALUES = new HashSet<>(Arrays.asList("lighting", "e-cigarette", "hairdresser", "pet"));
    private static final HashSet<String> ADDITIONAL_TOURISM_VALUES = new HashSet<>(Arrays.asList("artwork"));
    private static final HashSet<String> ADDITIONAL_MAN_MADE_VALUES = new HashSet<>(Arrays.asList("works"));

    public static HashSet<Pair<String, String>> REVERSED_GC_TAG_PARIS = new HashSet<>();

    PhotonReverseGeoCodingParserConstant(){
        initializeAllowedTags(REVERSED_GC_TAG_PARIS);
        initializeAdditionalTagsForGeocoding();
    }

    private void initializeAdditionalTagsForGeocoding(){
        addKeyValues(REVERSED_GC_TAG_PARIS, "amenity", ADDITIONAL_AMENITY_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "highway", ADDITIONAL_HIGHWAY_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "place", ADDITIONAL_PLACE_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "building", ADDITIONAL_BUILDING_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "railway", ADDITIONAL_RAILWAY_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "landuse", ADDITIONAL_LAND_USE_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "boundary", ADDITIONAL_BOUNDARY_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "shop", ADDITIONAL_SHOP_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "tourism", ADDITIONAL_TOURISM_VALUES);
        addKeyValues(REVERSED_GC_TAG_PARIS, "man_made", ADDITIONAL_MAN_MADE_VALUES);
    }

}
