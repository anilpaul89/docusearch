package com.hevodata.docusearch.extractor.init;

import com.hevodata.docusearch.extractor.consumer.DocumentExtractorConsumer;
import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import com.hevodata.docusearch.model.SourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitiateDocumentExtractor implements CommandLineRunner {

    @Autowired
    private DocumentExtractorConsumer documentExtractorConsumer;

    @Autowired
    @Qualifier("extractorProducerTemplate")
    private ReactiveKafkaProducerTemplate<String, DocumentExtractorRequest> kafkaProducerTemplate;

    @Override
    public void run(String... args) throws Exception {
        documentExtractorConsumer.consume().subscribe();
        DocumentExtractorRequest extractorRequest = new DocumentExtractorRequest();
        extractorRequest.setSource(SourceEnum.DROPBOX);
        kafkaProducerTemplate.send("extractor-request-v1", extractorRequest).subscribe();
    }
}
