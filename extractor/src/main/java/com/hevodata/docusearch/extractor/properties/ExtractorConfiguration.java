package com.hevodata.docusearch.extractor.properties;

import com.hevodata.docusearch.extractor.properties.dropbox.DropBoxProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "docusearch.system")
public class ExtractorConfiguration {

    private DropBoxProperties dropbox;

    private KafkaConfiguration kafka;
}
