package com.hevodata.docusearch.api.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import com.hevodata.docusearch.api.entity.DocumentMetadataEntity;
import com.hevodata.docusearch.api.repository.DocumentMetadataRepository;
import com.hevodata.docusearch.api.service.DocumentSearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class DocumentSearchServiceImpl implements DocumentSearchService {

    private DocumentMetadataRepository documentMetadataRepository;

    private ReactiveElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Flux<DocumentMetadataEntity> search(String query, String cursor) {
        Criteria c = new Criteria("title").contains(query);
        Query q = new CriteriaQuery(c);
        return elasticsearchTemplate.search(q, DocumentMetadataEntity.class)
                .map(hit -> hit.getContent());
    }
}
