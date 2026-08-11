package com.sevenlittlefish.knowledge.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "embedding-model")
public class EmbeddingModelProp {

    private String url;
}
