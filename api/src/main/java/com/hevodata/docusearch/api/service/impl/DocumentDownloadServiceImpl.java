package com.hevodata.docusearch.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hevodata.docusearch.api.properties.ApiConfiguration;
import com.hevodata.docusearch.api.service.DocumentDownloadService;
import com.hevodata.docusearch.model.SourceEnum;
import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;


import java.util.HashMap;
import java.util.Map;

import static com.hevodata.docusearch.constants.CommonConstants.*;
import static com.hevodata.docusearch.constants.DropBoxConstants.DROPBOX_API_ARG;
import static com.hevodata.docusearch.constants.DropBoxConstants.PATH;

@Service
@AllArgsConstructor
public class DocumentDownloadServiceImpl implements DocumentDownloadService {

    private WebClient webClient;

    private ApiConfiguration apiConfiguration;

    private ObjectMapper objectMapper;

    @Override
    public Flux<DataBuffer> download(SourceEnum source, String path) {
        return webClient.post()
                .header(AUTHORIZATION, BEARER + SPACE + apiConfiguration.getDropbox().getAccessToken())
                .header(DROPBOX_API_ARG, header(path))
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .onErrorMap(m -> {
                    m.printStackTrace();
                    WebClientResponseException exception = (WebClientResponseException) m;
                    System.out.println(exception.getResponseBodyAsString());
                    return m;
                });
    }

    private String header(String path) {
        Map<String, String> header = new HashMap<>();
        header.put(PATH, path);
        try {
            return objectMapper.writeValueAsString(header);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
