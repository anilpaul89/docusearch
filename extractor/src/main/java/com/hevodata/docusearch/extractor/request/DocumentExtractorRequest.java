package com.hevodata.docusearch.extractor.request;

import com.hevodata.docusearch.model.SourceEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DocumentExtractorRequest {

    private Map<String, String> metadata = new HashMap<>();

    private SourceEnum source;
}
