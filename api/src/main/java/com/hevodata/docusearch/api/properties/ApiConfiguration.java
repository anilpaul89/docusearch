package com.hevodata.docusearch.api.properties;

import com.hevodata.docusearch.api.properties.dropbox.DropBoxProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "docusearch.system")
public class ApiConfiguration {

    private DropBoxProperties dropbox;
}
