package com.hevodata.docusearch.api.controller;

import com.hevodata.docusearch.api.entity.DocumentMetadataEntity;
import com.hevodata.docusearch.api.service.DocumentSearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("v1/search")
public class DocumentSearchController {

    private DocumentSearchService documentSearchService;

    @GetMapping
    public Flux<DocumentMetadataEntity> search(@RequestParam(name = "q", required = false, defaultValue = "") String query,
                                               @RequestParam(required = false) String cursor) {
        log.info("Searching for document with query : {} and cursor: {}", query, cursor);
        return documentSearchService.search(query, cursor);
    }
}
