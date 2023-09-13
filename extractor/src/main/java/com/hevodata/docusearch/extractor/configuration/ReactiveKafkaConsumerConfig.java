package com.hevodata.docusearch.extractor.configuration;

import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;

@Configuration
public class ReactiveKafkaConsumerConfig {

    @Bean
    public ReceiverOptions<String, DocumentExtractorRequest> kafkaReceiverOptions(@Value(value = "${docusearch.system.kafka.extractor}") String topic, KafkaProperties kafkaProperties) {
        ReceiverOptions<String, DocumentExtractorRequest> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(topic));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, DocumentExtractorRequest> reactiveKafkaConsumerTemplate(ReceiverOptions<String, DocumentExtractorRequest> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<String, DocumentExtractorRequest>(kafkaReceiverOptions);
    }
}
