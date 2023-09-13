package com.hevodata.docusearch.api.service;

import com.hevodata.docusearch.api.entity.DocumentMetadataEntity;
import reactor.core.publisher.Flux;

public interface DocumentSearchService {

     Flux<DocumentMetadataEntity> search(String query, String cursor);
}
