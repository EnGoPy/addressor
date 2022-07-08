package com.engobytes.addressor.service;

import com.engobytes.addressor.service.model.AutoFillSuggestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AutoSearchService {
    List<AutoFillSuggestion> getSuggestionsByNamePart(String namePart);
}
