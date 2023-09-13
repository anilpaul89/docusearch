package com.hevodata.docusearch.extractor.model.dropbox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DropboxRequestModel {

    private String path;

    private String cursor;
}
