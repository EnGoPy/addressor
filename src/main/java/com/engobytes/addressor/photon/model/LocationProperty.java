package com.engobytes.addressor.photon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationProperty {
    private Long osm_id;
    private String osm_type;
    private String osm_key;
    private String osm_value;

    private String country;
    private String countrycode;

    private String city;
    private String postcode;
    private String district;
    private String street;
    private String housenumber;
    private String name;
    private String type;
}
