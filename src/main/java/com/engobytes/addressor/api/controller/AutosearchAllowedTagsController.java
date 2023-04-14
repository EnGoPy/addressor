package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.TagApi;
import com.engobytes.addressor.configuration.LocationSearchProperty;
import com.engobytes.addressor.mapper.TagMapper;
import com.engobytes.addressor.photon.constants.FilterTagUtils;
import com.engobytes.addressor.photon.constants.PhotonAutoSearchParserConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/autosearch/tags")
public class AutosearchAllowedTagsController {
    private static final Logger log = LoggerFactory.getLogger(AutosearchAllowedTagsController.class.getName());

    @Autowired
    private PhotonAutoSearchParserConstants photonAutoSearchParserConstants;
    @Autowired
    private LocationSearchProperty locationSearchProperty;

    @PostMapping
    public HttpStatus addAutoSearchTag(@RequestBody TagApi tag) {
        photonAutoSearchParserConstants.addAdditionalTag(TagMapper.fromApi(tag));
        return HttpStatus.CREATED;
    }

    @GetMapping
    public TreeMap<String, List<String>> allowedAutoSearchTags() {
        log.info("Reading AS tag list");
        return FilterTagUtils.groupElementsByKey(PhotonAutoSearchParserConstants.ALLOWED_TAG_PAIRS);
    }

    @PatchMapping(value = "/filtering")
    public HttpStatus toggleAutosearchFiltering(@RequestParam boolean filteringState){
        locationSearchProperty.setFilterAutosearchWithAllowedTags(filteringState);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public HttpStatus removeAutoSearchTag(@RequestBody TagApi tag) {
        log.info("Removing "+ tag);
        photonAutoSearchParserConstants.removeTag(TagMapper.fromApi(tag));
        return HttpStatus.ACCEPTED;
    }
}
