package com.engobytes.addressor.photon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Geometry {
    private double[] coordinates;

    public double getLon(){
        if(coordinates != null) {
            return coordinates[0];
        }else{
            return 0d;
        }
    }
    public double getLat(){
        if(coordinates != null) {
            return coordinates[1];
        }else{
            return 0d;
        }
    }
}
