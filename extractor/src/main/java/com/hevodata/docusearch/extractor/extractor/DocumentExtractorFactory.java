package com.hevodata.docusearch.extractor.extractor;

import com.hevodata.docusearch.extractor.extractor.impl.DropBoxExtractorServiceImpl;
import com.hevodata.docusearch.model.SourceEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DocumentExtractorFactory {

    private DropBoxExtractorServiceImpl dropBoxExtractorService;

    public DocumentExtractorService getExtractor(SourceEnum source) {
        DocumentExtractorService documentExtractorService = null;
        switch (source) {
            case DROPBOX:
                documentExtractorService = dropBoxExtractorService;
                break;
            default:
                break;
        }
        return documentExtractorService;
    }
}
