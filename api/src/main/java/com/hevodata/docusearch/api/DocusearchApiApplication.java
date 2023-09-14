package com.hevodata.docusearch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableElasticsearchRepositories
public class DocusearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocusearchApiApplication.class, args);
	}

}
