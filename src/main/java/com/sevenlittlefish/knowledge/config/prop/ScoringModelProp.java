package com.sevenlittlefish.knowledge.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "scoring-model")
public class ScoringModelProp {

    private String apiKey;

    private String modelName;

    private String baseUrl;
}
