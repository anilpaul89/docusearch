package com.hevodata.docusearch.extractor.model.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Data
public class DropboxResponseModel {

    @JsonProperty(value = "has_more")
    private boolean hasMore;

    private String cursor;

    private List<Map<String, Object>> entries;
}
