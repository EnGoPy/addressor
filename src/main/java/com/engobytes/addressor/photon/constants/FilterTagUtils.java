package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FilterTagUtils {

    public static TreeMap<String, List<String>> groupElementsByKey(Set<Pair<String, String>> pairHashSet) {
        Map<String, List<String>> setOfLists = pairHashSet
                .stream()
                .collect(Collectors
                        .groupingBy(Pair::getKey,
                                Collectors.mapping(Pair::getValue, Collectors.toList())));
        TreeMap<String, List<String>> sorted = new TreeMap<>(String::compareTo);
        sorted.putAll(setOfLists);
        return sorted;
    }

    public static void addKeyValues(Set<Pair<String, String>> tagPairs, String key, HashSet<String> values) {
        for (String value : values) {
            tagPairs.add(Pair.of(key, value));
        }
    }

    public static void addCustomTags(Set<Pair<String, String>> allowedTagContainer, Map<String,String> tagsToAdd){
        for(String key : tagsToAdd.keySet()){
            allowedTagContainer.add(Pair.of(key, tagsToAdd.get(key)));
        }
    }

    public static void removeCustomTags(Set<Pair<String, String>> allowedTagContainer, Map<String, String> tagsToRemove){
        for(String key : tagsToRemove.keySet()){
            allowedTagContainer.remove(Pair.of(key, tagsToRemove.get(key)));
        }
    }
}
