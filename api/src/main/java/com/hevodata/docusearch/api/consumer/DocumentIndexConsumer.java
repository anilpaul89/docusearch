package com.hevodata.docusearch.api.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hevodata.docusearch.api.service.DocumentIndexService;
import com.hevodata.docusearch.model.DocumentIndexModel;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DocumentIndexConsumer {

    private ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate;

    private DocumentIndexService documentIndexService;

    private ObjectMapper objectMapper;

    public Flux<String> consume() {
        return reactiveKafkaConsumerTemplate.receiveAutoAck()
                .flatMap(consumerRecord -> {
                    String value = consumerRecord.value();
                    try {
                        return extract(objectMapper.readValue(value, DocumentIndexModel.class));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return Mono.empty();
                })
                .log("Extracted documents");

    }

    private Mono<String> extract(DocumentIndexModel documentIndexModel) {
        return this.documentIndexService.index(documentIndexModel);
    }
}
