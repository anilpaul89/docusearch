package com.hevodata.docusearch.extractor;

import com.hevodata.docusearch.extractor.extractor.DocumentExtractorFactory;
import com.hevodata.docusearch.extractor.model.ExtractorResponse;
import com.hevodata.docusearch.extractor.request.DocumentExtractorRequest;
import com.hevodata.docusearch.model.SourceEnum;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/test")
@AllArgsConstructor
public class TestController {

    private DocumentExtractorFactory documentExtractorFactory;

    @GetMapping
    public Mono<ExtractorResponse> extract() {
        DocumentExtractorRequest documentExtractorRequest = new DocumentExtractorRequest();
        documentExtractorRequest.setSource(SourceEnum.DROPBOX);
        return this.documentExtractorFactory.getExtractor(documentExtractorRequest.getSource()).extract(documentExtractorRequest);
    }
}
