package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.photon.constants.FilterTagUtils;
import com.engobytes.addressor.photon.constants.PhotonReverseGeoCodingParserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController(value = "/reverse/tags")
public class ReversedGeocodeAllowedTagsController {

    @Autowired
    private PhotonReverseGeoCodingParserConstant photonReverseGeoCodingParserConstant;

    @PostMapping
    public HttpStatus addTag(@RequestBody Map<String, String> tag) {
        photonReverseGeoCodingParserConstant.addAdditionalTag(tag);
        return HttpStatus.CREATED;
    }

    @GetMapping
    public TreeMap<String, List<String>> allowedTags() {
        return FilterTagUtils.groupElementsByKey(PhotonReverseGeoCodingParserConstant.REVERSED_GC_TAG_PARIS);
    }

    @DeleteMapping
    public HttpStatus removeTag(@RequestBody Map<String, String> tag) {
        photonReverseGeoCodingParserConstant.removeTag(tag);
        return HttpStatus.ACCEPTED;
    }

}
