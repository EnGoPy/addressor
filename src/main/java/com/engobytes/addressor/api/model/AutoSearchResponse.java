package com.engobytes.addressor.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AutoSearchResponse {
    private List<AutoFillResponse> autoFilledProposals;
}
