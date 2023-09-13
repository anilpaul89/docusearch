package com.hevodata.docusearch.api.init;

import com.hevodata.docusearch.api.consumer.DocumentIndexConsumer;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitiateKafkaConsumer implements CommandLineRunner {

    private DocumentIndexConsumer documentIndexConsumer;

    @Override
    public void run(String... args) throws Exception {
        this.documentIndexConsumer.consume().subscribe();
    }
}
