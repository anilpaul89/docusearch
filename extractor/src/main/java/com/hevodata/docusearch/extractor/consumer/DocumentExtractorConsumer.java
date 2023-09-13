package com.hevodata.docusearch.extractor.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hevodata.docusearch.extractor.extractor.DocumentExtractorFactory;
import com.hevodata.docusearch.extractor.model.ExtractorResponse;
import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DocumentExtractorConsumer {

    private ReactiveKafkaConsumerTemplate<String, DocumentExtractorRequest> reactiveKafkaConsumerTemplate;

    private DocumentExtractorFactory documentExtractorFactory;

    private ObjectMapper objectMapper;

    public Flux<ExtractorResponse> consume() {
        return reactiveKafkaConsumerTemplate.receive()
                .flatMap(consumerRecord -> extract(consumerRecord.value()))
                .log("Extracted documents");

    }

    private Mono<ExtractorResponse> extract(DocumentExtractorRequest documentExtractorRequest) {
        return this.documentExtractorFactory.getExtractor(documentExtractorRequest.getSource()).extract(documentExtractorRequest);
    }
}
