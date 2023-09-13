package com.hevodata.docusearch.api.controller;

import com.hevodata.docusearch.api.service.DocumentDownloadService;
import com.hevodata.docusearch.model.SourceEnum;
import lombok.AllArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("v1/download")
public class DocumentDownloadController {

    private DocumentDownloadService documentDownloadService;

    @GetMapping("/{path}")
    public ResponseEntity<Flux<DataBuffer>> download(@RequestParam SourceEnum source, @PathVariable String path) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(this.documentDownloadService.download(source, path));

    }
}
