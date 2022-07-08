package com.engobytes.addressor.api.model;

import com.engobytes.addressor.service.model.MatchPair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutofillLocationDescription {
    private String title;
    private String address;
    private List<MatchPair> matchPairs;
}
