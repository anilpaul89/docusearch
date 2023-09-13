package com.hevodata.docusearch.extractor.extractor.impl;

import com.hevodata.docusearch.extractor.model.dropbox.DropboxRequestModel;
import com.hevodata.docusearch.extractor.model.dropbox.DropboxResponseModel;
import com.hevodata.docusearch.extractor.model.ExtractorResponse;
import com.hevodata.docusearch.extractor.properties.ExtractorConfiguration;
import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import com.hevodata.docusearch.extractor.extractor.DocumentExtractorService;
import com.hevodata.docusearch.model.DocumentIndexModel;
import com.hevodata.docusearch.model.SourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.hevodata.docusearch.constants.CommonConstants.*;
import static com.hevodata.docusearch.constants.DropBoxConstants.*;

@Service
public class DropBoxExtractorServiceImpl extends DocumentExtractorService {

    @Autowired
    public DropBoxExtractorServiceImpl(ReactiveKafkaProducerTemplate<String, DocumentIndexModel> indexProducer,
                                       ReactiveKafkaProducerTemplate<String, DocumentExtractorRequest> extractorProducer,
                                       ExtractorConfiguration extractorConfiguration) {
        super(indexProducer, extractorProducer, extractorConfiguration);
    }

    public Mono<ExtractorResponse> extract(DocumentExtractorRequest request) {
        return this.getDetails(request.getMetadata().getOrDefault("path", ""), null, true)
                .expand(response -> this.getDetails(null, response.getCursor(), response.isHasMore()))
                .flatMapIterable(object -> object.getEntries())
                .flatMap(entry -> processResponse(entry)).last()
                .map(t -> ExtractorResponse.builder().success(true).build());
    }


    private Mono<DropboxResponseModel> getDetails(String path, String cursor, boolean hasMore) {
        if (!hasMore) {
            return Mono.empty();
        }
        WebClient webClient = WebClient.builder()
                .baseUrl(extractorConfiguration.getDropbox().getBaseUrl())
                .defaultHeader(AUTHORIZATION, BEARER + SPACE + extractorConfiguration.getDropbox().getAccessToken())
                .build();
        DropboxRequestModel requestModel = DropboxRequestModel.builder()
                .path(path)
                .cursor(cursor)
                .build();
        return webClient.post().uri(extractorConfiguration.getDropbox().getListUrl())
                .body(BodyInserters.fromValue(requestModel))
                .retrieve()
                .bodyToMono(DropboxResponseModel.class);
    }

    private Mono<String> processResponse(Map<String, Object> fileDetails) {
        String type = (String) fileDetails.get(TAG);
        if (FOLDER.equalsIgnoreCase(type)) {
            DocumentExtractorRequest documentExtractorRequest = new DocumentExtractorRequest();
            documentExtractorRequest.setSource(SourceEnum.DROPBOX);
            Map<String, String> metadata = new HashMap<>();
            metadata.put("path", (String) fileDetails.get("path_lower"));
            documentExtractorRequest.setMetadata(metadata);
            return publishToExtract(documentExtractorRequest);
        } else if (FILE.equalsIgnoreCase(type)) {
            DocumentIndexModel documentIndexModel = DocumentIndexModel
                    .builder()
                    .source(SourceEnum.DROPBOX)
                    .id((String) fileDetails.get("id"))
                    .name((String) fileDetails.get("name"))
                    .path((String) fileDetails.get("path_lower"))
                    .build();
            return publish(documentIndexModel);
        }
        return Mono.empty();
    }
}

