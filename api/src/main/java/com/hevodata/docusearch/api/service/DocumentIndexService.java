package com.hevodata.docusearch.api.service;

import com.hevodata.docusearch.model.DocumentIndexModel;
import reactor.core.publisher.Mono;

public interface DocumentIndexService {

     Mono<String> index(DocumentIndexModel documentIndexModel);
}
