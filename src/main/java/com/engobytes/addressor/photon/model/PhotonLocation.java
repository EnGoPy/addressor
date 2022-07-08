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
public class PhotonLocation {
    private Geometry geometry = new Geometry();
    private String type;
    private LocationProperty properties = new LocationProperty();
}
