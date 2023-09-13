package com.hevodata.docusearch.extractor.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaConfiguration {

    private String extractor;

    private String indexer;
}
