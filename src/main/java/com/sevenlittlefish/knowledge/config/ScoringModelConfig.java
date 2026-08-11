package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.common.constant.CommonConstants;
import com.sevenlittlefish.knowledge.config.prop.ScoringModelProp;
import dev.langchain4j.model.jina.JinaScoringModel;
import dev.langchain4j.model.scoring.ScoringModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class ScoringModelConfig {

    @Resource
    private ScoringModelProp scoringModelProp;

    @Bean
    public ScoringModel scoringModel() {
        return JinaScoringModel.builder()
                .apiKey(scoringModelProp.getApiKey())
                .modelName(scoringModelProp.getModelName())
                .timeout(Duration.ofMillis(CommonConstants.CHAT_MODEL_TIMEOUT_MILLIS))
                .build();
    }
}
