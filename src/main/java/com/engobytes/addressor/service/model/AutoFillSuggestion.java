package com.engobytes.addressor.service.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AutoFillSuggestion {

    @EqualsAndHashCode.Exclude
    private double latitude;
    @EqualsAndHashCode.Exclude
    private double longitude;
    private String title;
    private String address;

    public String getReversedDescription(){
        return this.title + ", " + this.address;
    }
}
