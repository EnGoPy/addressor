package com.engobytes.addressor.utils;

import com.engobytes.addressor.service.model.MatchPair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {

    @Test
    public void shouldNotCleanMiddleWords(){
        String sentenceToClean = "Content that some we need Some to clean 333-232";
        String unwantedWord = "some";

        String cleanedSentence = WordParser.eraseFinishingStrings(sentenceToClean, unwantedWord);

        assertEquals(sentenceToClean, cleanedSentence);
    }

    @Test
    public void shouldNotModifyWordWhenUnwantedWordIsEmpty(){
        String sentenceToClean = "Content that some we need Some to clean 333-232";
        String unwantedWord = "";

        String cleanedSentence = WordParser.eraseFinishingStrings(sentenceToClean, unwantedWord);

        assertEquals(sentenceToClean, cleanedSentence);
    }

    @Test
    public void shouldNotModifyWordWhenSentenceIsEmpty(){
        String sentenceToClean = "";
        String unwantedWord = "some";

        String cleanedSentence = WordParser.eraseFinishingStrings(sentenceToClean, unwantedWord);

        assertEquals(sentenceToClean, cleanedSentence);
    }

    @Test
    public void shouldReturnEmptyStringWhenSentenceIsBlank(){
        String sentenceToClean = " ";
        String unwantedWord = "some";

        String cleanedSentence = WordParser.eraseFinishingStrings(sentenceToClean, unwantedWord);

        assertTrue(cleanedSentence.isEmpty());
    }


    @Test
    public void shouldCleanEndsOfWordIgnoreCaseSensitivity(){
        String sentenceToClean = "Some Content that we need to clean 333-232 some";
        String unwantedWord = "some";

        String cleanedSentence = WordParser.eraseFinishingStrings(sentenceToClean, unwantedWord);

        assertFalse(cleanedSentence.contains(unwantedWord));
    }

    @Test
    public void shouldReturnCorrectSingleMatchPair(){
        String baseWord = "!@#$%^cat^#$&$";
        String findWord = "cat";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertEquals(1, matchedPair.size());
        assertEquals(6, matchedPair.get(0).getStartIndex());
        assertEquals(3, matchedPair.get(0).getLength());
    }

    @Test
    public void shouldReturnCorrectDoubleMatchPair(){
        String baseWord = "!@#$%^cat^#$cat&$";
        String findWord = "cat";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertEquals(2, matchedPair.size());
        assertEquals(6, matchedPair.get(0).getStartIndex());
        assertEquals(3, matchedPair.get(0).getLength());
        assertEquals(12, matchedPair.get(1).getStartIndex());
        assertEquals(3, matchedPair.get(1).getLength());
    }

    @Test
    public void shouldReturnEmptyMatchPair(){
        String baseWord = "!@#$%^at^#$ct&$";
        String findWord = "cat";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertTrue(matchedPair.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMatchPairIfNullBaseWordPassed(){
        String baseWord = null;
        String findWord = "cat";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertTrue(matchedPair.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMatchPairIfEmptyBaseWordPassed(){
        String baseWord = "";
        String findWord = "cat";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertTrue(matchedPair.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMatchPairIfNullFindWordPassed(){
        String baseWord = "aafsdFS";
        String findWord = null;
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertTrue(matchedPair.isEmpty());
    }

    @Test
    public void shouldReturnEmptyMatchPairIfEmptyFindWordPassed(){
        String baseWord = "aafsdFS@#F@";
        String findWord = "";
        List<MatchPair> matchedPair = WordParser.findMatchingPairsInString(baseWord, findWord);

        assertTrue(matchedPair.isEmpty());
    }

}