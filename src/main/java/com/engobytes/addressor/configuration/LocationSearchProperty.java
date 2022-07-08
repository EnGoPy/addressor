package com.engobytes.addressor.configuration;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LocationSearchProperty {
    private int autoSearchResultLimit;
}
