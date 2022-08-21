package com.engobytes.addressor.photon.constants;

import com.engobytes.addressor.service.model.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class FilterTagUtilsTest {


    @Test
    public void whenEmptySetPassedEmptyMapReturned(){
        Set<Pair<String, String>> emptySet = new HashSet<>();

        TreeMap<String, List<String>> returnedMap = FilterTagUtils.groupElementsByKey(emptySet);

        assertTrue(returnedMap.isEmpty());
    }

    @Test
    public void whenNullPassedEmptyMapReturned(){
        Set<Pair<String, String>> nullSet = null;

        TreeMap<String, List<String>> returnedMap = FilterTagUtils.groupElementsByKey(nullSet);

        assertTrue(returnedMap.isEmpty());
    }

    @Test
    public void whenCorrectSetOfPairsPassedCorrectMapReturned(){
        Set<Pair<String, String>> correctSetOfPairs = new HashSet<>();
        correctSetOfPairs.add(Pair.of("key", "value1"));
        correctSetOfPairs.add(Pair.of("key", "value2"));
        correctSetOfPairs.add(Pair.of("key", "value3"));
        correctSetOfPairs.add(Pair.of("anotherKey", "value1"));
        correctSetOfPairs.add(Pair.of("anotherKey", "value3"));

        TreeMap<String, List<String>> returnedMap = FilterTagUtils.groupElementsByKey(correctSetOfPairs);

        assertFalse(returnedMap.isEmpty());
        assertEquals(2, returnedMap.keySet().size());
        assertEquals(3, returnedMap.get("key").size());
        assertEquals(2, returnedMap.get("anotherKey").size());
    }

    @Test
    public void whenNullableSetPassedNoExceptionThrown(){
        Set<Pair<String, String>> tagPairs = null;
        String key = "key";
        Set<String> values = new HashSet<>(Arrays.asList("v1", "v2", "v3"));

        FilterTagUtils.addKeyValues(tagPairs, key, values);

        assertNull(tagPairs);
    }

    @Test
    public void whenKeyPassedNoExceptionThrown(){
        Set<Pair<String, String>> tagPairs = new HashSet<>();
        String key = null;
        Set<String> values = new HashSet<>(Arrays.asList("v1", "v2", "v3"));

        FilterTagUtils.addKeyValues(tagPairs, key, values);

        assertNull(tagPairs);
    }

    @Test
    public void whenCorrectDataPassedCorrectSet(){
        Set<Pair<String, String>> tagPairs = new HashSet<>();
        String key = "key";
        Set<String> values = new HashSet<>(Arrays.asList("v1", "v2", "v3"));

        FilterTagUtils.addKeyValues(tagPairs, key, values);

        assertEquals(3, tagPairs.size());
        assertTrue(tagPairs.contains(Pair.of("key", "v1")));
        assertTrue(tagPairs.contains(Pair.of("key", "v2")));
        assertTrue(tagPairs.contains(Pair.of("key", "v3")));
    }

}