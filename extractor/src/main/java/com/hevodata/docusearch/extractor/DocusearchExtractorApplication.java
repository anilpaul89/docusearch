package com.hevodata.docusearch.extractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class DocusearchExtractorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocusearchExtractorApplication.class, args);
    }

}
