package com.hevodata.docusearch.api.service;

import com.hevodata.docusearch.model.SourceEnum;
import org.springframework.core.io.buffer.DataBuffer;
import reactor.core.publisher.Flux;


public interface DocumentDownloadService {

    Flux<DataBuffer> download(SourceEnum source, String path);
}
