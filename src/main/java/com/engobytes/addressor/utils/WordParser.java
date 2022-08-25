package com.engobytes.addressor.utils;

import com.engobytes.addressor.service.model.MatchPair;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WordParser {

    public static String getValueOrNothing(String word, SufixEnum prefix) {
        if (word == null || "".equals(word)) {
            return "";
        } else {
            return prefix.getSign() + word;
        }
    }

    public static String eraseFinishingStrings(@NotNull String word, @NotNull String unWantedWord) {
        if(unWantedWord.isEmpty()){
            return word;
        }
        int startIndex = 0;
        int endIndex = word.length();
        if (word.startsWith(unWantedWord)) {
            word = eraseFinishingStrings(word.substring(startIndex + unWantedWord.length(), endIndex), unWantedWord);
        } else if (word.startsWith(" ")) {
            word = eraseFinishingStrings(word.substring(startIndex + 1, endIndex), unWantedWord);
        }
        if (word.endsWith(unWantedWord)) {
            word = eraseFinishingStrings(word.substring(startIndex, endIndex - unWantedWord.length()), unWantedWord);
        } else if (word.endsWith(" ")) {
            word = eraseFinishingStrings(word.substring(startIndex, endIndex - 1), unWantedWord);
        }
        return word;
    }

    public static List<MatchPair> findMatchingPairsInString(String baseWord, String findSubString) {
        List<MatchPair> matchedPairs = new ArrayList<>();
        if (baseWord == null || baseWord.isEmpty()) {
            return matchedPairs;
        }
        if (findSubString == null || findSubString.isEmpty()) {
            return matchedPairs;
        }
        if(baseWord.contains(findSubString)) {
            for (int i = 0; i <= (baseWord.length() - findSubString.length()); i++) {
                if (baseWord.toLowerCase().substring(i, i + findSubString.length()).equals(findSubString.toLowerCase())) {
                    matchedPairs.add(new MatchPair(i, findSubString.length()));
                }
            }
        }
        return matchedPairs;
    }
}
