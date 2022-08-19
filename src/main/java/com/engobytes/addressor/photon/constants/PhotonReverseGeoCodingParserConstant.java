package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class PhotonReverseGeoCodingParserConstant{

    protected static final HashSet<String> ADDITIONAL_AMENITY_VALUES = new HashSet<>(Arrays.asList( "bench", "bicycle_parking", "charging_station", "parking", "police", "vending_machine", "veterinary"));
    protected static final HashSet<String> ADDITIONAL_HIGHWAY_VALUES = new HashSet<>(Arrays.asList("bus_stop", "platform", "primary",  "proposed", "tertiary"));
    protected static final HashSet<String> ADDITIONAL_BUILDING_VALUES = new HashSet<>(Arrays.asList("commercial", "dormitory", "kindergarten", "industrial", "office", "university", "retail"));
    protected static final HashSet<String> ADDITIONAL_PLACE_VALUES = new HashSet<>(Arrays.asList("city", "house"));
    protected static final HashSet<String> ADDITIONAL_RAILWAY_VALUES = new HashSet<>(Arrays.asList("platform"));
    protected static final HashSet<String> ADDITIONAL_LAND_USE_VALUES = new HashSet<>(Arrays.asList("construction", "residential"));
    protected static final HashSet<String> ADDITIONAL_BOUNDARY_VALUES = new HashSet<>(Arrays.asList("administrative"));
    protected static final HashSet<String> ADDITIONAL_SHOP_VALUES = new HashSet<>(Arrays.asList("lighting", "e-cigarette", "hairdresser", "pet"));
    protected static final HashSet<String> ADDITIONAL_TOURISM_VALUES = new HashSet<>(Arrays.asList("artwork"));
    protected static final HashSet<String> ADDITIONAL_MAN_MADE_VALUES = new HashSet<>(Arrays.asList("works"));

    private final Map<String, String> addedCustomTags = new HashMap<>();
    private final Map<String, String> removeCustomTags = new HashMap<>();

    public static Set<Pair<String, String>> REVERSED_GC_TAG_PARIS = new HashSet<>();

    PhotonReverseGeoCodingParserConstant(){
        initializeAutoSearchAllowedParts();
        initializeAdditionalTagsForGeocoding();
    }

    private void initializeAutoSearchAllowedParts(){
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS,"shop" , PhotonAutoSearchParserConstants.SHOP_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "highway", PhotonAutoSearchParserConstants.HIGHWAY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "building", PhotonAutoSearchParserConstants.BUILDING_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "amenity", PhotonAutoSearchParserConstants.AMENITY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "office", PhotonAutoSearchParserConstants.OFFICE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "leisure", PhotonAutoSearchParserConstants.LEISURE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "tourism", PhotonAutoSearchParserConstants.TOURISM_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "landuse", PhotonAutoSearchParserConstants.LAND_USE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "man_made", PhotonAutoSearchParserConstants.MAN_MADE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "waterway", PhotonAutoSearchParserConstants.WATERWAY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "place", PhotonAutoSearchParserConstants.PLACE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "historic", PhotonAutoSearchParserConstants.HISTORIC_VALUES);
    }

    private void initializeAdditionalTagsForGeocoding(){
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "amenity", ADDITIONAL_AMENITY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "highway", ADDITIONAL_HIGHWAY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "place", ADDITIONAL_PLACE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "building", ADDITIONAL_BUILDING_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "railway", ADDITIONAL_RAILWAY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "landuse", ADDITIONAL_LAND_USE_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "boundary", ADDITIONAL_BOUNDARY_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "shop", ADDITIONAL_SHOP_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "tourism", ADDITIONAL_TOURISM_VALUES);
        FilterTagUtils.addKeyValues(REVERSED_GC_TAG_PARIS, "man_made", ADDITIONAL_MAN_MADE_VALUES);
        FilterTagUtils.addCustomTags(REVERSED_GC_TAG_PARIS, addedCustomTags);
        FilterTagUtils.removeCustomTags(REVERSED_GC_TAG_PARIS, removeCustomTags);
    }

    public void addAdditionalTag(Map<String, String> tag){
        FilterTagUtils.addCustomTags(REVERSED_GC_TAG_PARIS, tag);
    }

    public void removeTag(Map<String, String> tag){
        FilterTagUtils.removeCustomTags(REVERSED_GC_TAG_PARIS, tag);
    }

}
