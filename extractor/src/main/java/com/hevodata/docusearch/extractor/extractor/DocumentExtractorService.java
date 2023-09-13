package com.hevodata.docusearch.extractor.extractor;

import com.hevodata.docusearch.extractor.model.ExtractorResponse;
import com.hevodata.docusearch.extractor.properties.ExtractorConfiguration;
import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import com.hevodata.docusearch.model.DocumentIndexModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public abstract class DocumentExtractorService {

    @Qualifier(value = "indexProducerTemplate")
    private ReactiveKafkaProducerTemplate<String, DocumentIndexModel> indexProducerTemplate;

    @Qualifier(value = "extractorProducerTemplate")
    private ReactiveKafkaProducerTemplate<String, DocumentExtractorRequest> extractorProducerTemplate;

    protected ExtractorConfiguration extractorConfiguration;

    public abstract Mono<ExtractorResponse> extract(DocumentExtractorRequest documentExtractorRequest);

    protected Mono<String> publish(DocumentIndexModel model) {
        return this.indexProducerTemplate.send(extractorConfiguration.getKafka().getIndexer(), model)
                .map(senderResult -> senderResult.recordMetadata().topic());
    }

    protected Mono<String> publishToExtract(DocumentExtractorRequest request) {
        return this.extractorProducerTemplate.send(extractorConfiguration.getKafka().getExtractor(), request)
                .map(senderResult -> senderResult.recordMetadata().topic());
    }


}
