package com.hevodata.docusearch.api.service.impl;

import com.hevodata.docusearch.api.entity.DocumentMetadataEntity;
import com.hevodata.docusearch.api.repository.DocumentMetadataRepository;
import com.hevodata.docusearch.api.service.DocumentIndexService;
import com.hevodata.docusearch.model.DocumentIndexModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DocumentIndexServiceImpl implements DocumentIndexService {

    private DocumentMetadataRepository documentMetadataRepository;

    @Override
    public Mono<String> index(DocumentIndexModel documentIndexModel) {
        DocumentMetadataEntity metadata = new DocumentMetadataEntity();
        metadata.setId(documentIndexModel.getId());
        metadata.setTitle(documentIndexModel.getName());
        metadata.setDownloadPath(documentIndexModel.getPath());
        metadata.setSource(documentIndexModel.getSource());
        return this.documentMetadataRepository.save(metadata)
                .map(m -> m.getId());

    }
}
