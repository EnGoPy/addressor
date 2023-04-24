package com.engobytes.addressor.mapper;

import com.engobytes.addressor.api.model.ReversedSettingsApi;
import com.engobytes.addressor.api.model.SearchPropertiesModel;

public class ReversedSettingsMapper {

    public static ReversedSettingsApi fromModelToApi(SearchPropertiesModel model){
        if(model != null){
            return new ReversedSettingsApi(model.getReverseGeocodingFiltering());
        }
        return null;
    }
}
