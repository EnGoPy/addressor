package com.engobytes.addressor.api.controller;

import com.engobytes.addressor.api.model.TagApi;
import com.engobytes.addressor.mapper.TagMapper;
import com.engobytes.addressor.photon.constants.FilterTagUtils;
import com.engobytes.addressor.photon.constants.PhotonAutoSearchParserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/autosearch/tags")
public class AutosearchAllowedTagsController {

    @Autowired
    private PhotonAutoSearchParserConstants photonAutoSearchParserConstants;

    @PostMapping
    public HttpStatus addAutoSearchTag(@RequestBody TagApi tag) {
        photonAutoSearchParserConstants.addAdditionalTag(TagMapper.fromApi(tag));
        return HttpStatus.CREATED;
    }

    @GetMapping
    public TreeMap<String, List<String>> allowedAutoSearchTags() {
        return FilterTagUtils.groupElementsByKey(PhotonAutoSearchParserConstants.ALLOWED_TAG_PAIRS);
    }

    @DeleteMapping
    public HttpStatus removeAutoSearchTag(@RequestBody TagApi tag) {
        photonAutoSearchParserConstants.removeTag(TagMapper.fromApi(tag));
        return HttpStatus.ACCEPTED;
    }
}
