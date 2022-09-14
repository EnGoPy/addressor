package com.engobytes.addressor.mapper;

import com.engobytes.addressor.api.model.TagApi;
import com.engobytes.addressor.photon.model.Tag;

public class TagMapper {

    public static Tag fromApi(TagApi api) {
        if (api != null) {
            return new Tag(api.getKey(), api.getValue());
        }
        return null;
    }

    public static TagApi fromApi(Tag tag) {
        if (tag != null) {
            return new TagApi(tag.getKey(), tag.getValue());
        }
        return null;
    }
}
