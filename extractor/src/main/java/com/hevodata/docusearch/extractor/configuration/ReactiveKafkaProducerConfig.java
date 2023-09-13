package com.hevodata.docusearch.extractor.configuration;

import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import com.hevodata.docusearch.model.DocumentIndexModel;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class ReactiveKafkaProducerConfig {

    @Bean(name = "indexProducerTemplate")
    public ReactiveKafkaProducerTemplate<String, DocumentIndexModel> indexProducerTemplate(KafkaProperties kafkaProperties) {
        return new ReactiveKafkaProducerTemplate<String, DocumentIndexModel>(SenderOptions.create(kafkaProperties.buildProducerProperties()));
    }

    @Bean(name = "extractorProducerTemplate")
    public ReactiveKafkaProducerTemplate<String, DocumentExtractorRequest> extractorProducerTemplate(KafkaProperties kafkaProperties) {
        return new ReactiveKafkaProducerTemplate<String, DocumentExtractorRequest>(SenderOptions.create(kafkaProperties.buildProducerProperties()));
    }

}
