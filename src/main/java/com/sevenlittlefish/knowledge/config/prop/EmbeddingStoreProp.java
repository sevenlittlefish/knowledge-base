package com.sevenlittlefish.knowledge.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "embedding-store")
public class EmbeddingStoreProp {

    private String host;

    private Integer port;
}
