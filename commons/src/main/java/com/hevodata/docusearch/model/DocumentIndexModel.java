package com.hevodata.docusearch.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class DocumentIndexModel {

    private String id;

    private String name;

    private String content;

    private Long size;

    private SourceEnum source;

    private String path;
}
