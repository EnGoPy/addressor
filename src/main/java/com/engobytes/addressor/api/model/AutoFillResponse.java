package com.engobytes.addressor.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoFillResponse {
    private double latitude;
    private double longitude;
    private AutofillLocationDescription description;
}
