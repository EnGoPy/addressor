package com.engobytes.addressor.configuration;

import com.engobytes.addressor.photon.constants.PhotonAutoSearchParserConstants;
import com.engobytes.addressor.photon.constants.PhotonReverseGeoCodingParserConstant;
import com.engobytes.addressor.service.model.Pair;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
@Endpoint(id = "allowedTags")
public class AllowedTagPairsInfoEndpoint {


    @ReadOperation
    public Map<String, Object> allowedTags() {
        Map<String, Object> allowedTags = new HashMap<>();
        allowedTags.put("geoCordSearch", groupElementsByKey(PhotonAutoSearchParserConstants.ALLOWED_TAG_PAIRS));
        allowedTags.put("reversedGeoCode", groupElementsByKey(PhotonReverseGeoCodingParserConstant.REVERSED_GC_TAG_PARIS));
        return allowedTags;
    }

    private TreeMap<String,List<String>> groupElementsByKey(HashSet<Pair<String, String>> pairHashSet){
        Map<String, List<String>> setOfLists = pairHashSet
                .stream()
                .collect(Collectors
                        .groupingBy(Pair::getKey,
                                Collectors.mapping(Pair::getValue, Collectors.toList())));
        TreeMap<String,List<String>> sorted = new TreeMap<>(String::compareTo);
        sorted.putAll(setOfLists);
        return sorted;
    }



}
