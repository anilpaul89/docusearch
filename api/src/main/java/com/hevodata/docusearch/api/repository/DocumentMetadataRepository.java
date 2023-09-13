package com.hevodata.docusearch.api.repository;

import com.hevodata.docusearch.api.entity.DocumentMetadataEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

public interface DocumentMetadataRepository extends ReactiveElasticsearchRepository<DocumentMetadataEntity, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}}]}}")
    Flux<DocumentMetadataEntity> findByTitleUsingCustomQuery(String title);
}
