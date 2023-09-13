package com.hevodata.docusearch.api.configuration;

import com.hevodata.docusearch.api.properties.ApiConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class WebClientConfiguration {

    private ApiConfiguration apiConfiguration;


    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(apiConfiguration.getDropbox().getBaseUrl())
                .build();
        return webClient;
    }

}
