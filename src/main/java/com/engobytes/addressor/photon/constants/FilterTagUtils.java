package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.photon.model.Tag;
import com.engobytes.addressor.service.model.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
public class FilterTagUtils {

    public static TreeMap<String, List<String>> groupElementsByKey(@Nullable Set<Pair<String, String>> pairHashSet) {
        if (pairHashSet == null) {
            return new TreeMap<>();
        }
        Map<String, List<String>> setOfLists = pairHashSet
                .stream()
                .collect(Collectors
                        .groupingBy(Pair::getKey,
                                Collectors.mapping(Pair::getValue, Collectors.toList())));
        TreeMap<String, List<String>> sorted = new TreeMap<>(String::compareTo);
        sorted.putAll(setOfLists);
        return sorted;
    }

    public static void addKeyValues(@Nullable Set<Pair<String, String>> tagPairs, @Nullable String key, Set<String> values) {
        if (tagPairs == null || key == null) {
            log.warn("Null parameter passed to method. Breaking execution. tagPairs: {} , key: {} ",
                    tagPairs == null, key == null);
            return;
        }
        for (String value : values) {
            tagPairs.add(Pair.of(key, value));
        }
    }

    public static void addCustomTags(Set<Pair<String, String>> allowedTagContainer, Map<String, String> tagsToAdd) {
        for (String key : tagsToAdd.keySet()) {
            allowedTagContainer.add(Pair.of(key, tagsToAdd.get(key)));
        }
    }

    public static void addSingleTag(Set<Pair<String, String>> allowedTagContainer, Tag tagToAdd) {
        allowedTagContainer.add(Pair.of(tagToAdd.getKey(), tagToAdd.getValue()));
    }

    public static void removeCustomTags(Set<Pair<String, String>> allowedTagContainer, Map<String, String> tagsToRemove) {
        for (String key : tagsToRemove.keySet()) {
            allowedTagContainer.remove(Pair.of(key, tagsToRemove.get(key)));
        }
    }

    public static void removeSingleTag(Set<Pair<String, String>> allowedTagContainer, Tag tagToRemove) {
        allowedTagContainer.remove(Pair.of(tagToRemove.getKey(), tagToRemove.getValue()));
    }
}
