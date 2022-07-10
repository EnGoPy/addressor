package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhotonReverseGeoCodingParserConstant extends PhotonAutoSearchParserConstants{

    private static final List<String> ADDITIONAL_AMENITY_VALUES = Arrays.asList( "bench", "bicycle_parking", "charging_station", "parking", "police", "vending_machine", "veterinary");
    private static final List<String> ADDITIONAL_HIGHWAY_VALUES = Arrays.asList("bus_stop", "platform", "primary",  "proposed", "tertiary");
    private static final List<String> ADDITIONAL_BUILDING_VALUES = Arrays.asList("commercial", "dormitory", "kindergarten", "industrial", "office", "university", "retail");
    private static final List<String> ADDITIONAL_PLACE_VALUES = Arrays.asList("city", "house");
    private static final List<String> ADDITIONAL_RAILWAY_VALUES = Arrays.asList("platform");
    private static final List<String> ADDITIONAL_LAND_USE_VALUES = Arrays.asList("construction", "residential");
    private static final List<String> ADDITIONAL_BOUNDARY_VALUES = Arrays.asList("administrative");
    private static final List<String> ADDITIONAL_SHOP_VALUES = Arrays.asList("lighting", "e-cigarette", "hairdresser", "pet");
    private static final List<String> ADDITIONAL_TOURISM_VALUES = Arrays.asList("artwork");
    private static final List<String> ADDITIONAL_MAN_MADE_VALUES = Arrays.asList("works");

    public static List<Pair<String, String>> REVERSED_GC_TAG_PARIS;

    PhotonReverseGeoCodingParserConstant() {
        REVERSED_GC_TAG_PARIS = new ArrayList<>();
        initializeAllowedTags(REVERSED_GC_TAG_PARIS);

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
