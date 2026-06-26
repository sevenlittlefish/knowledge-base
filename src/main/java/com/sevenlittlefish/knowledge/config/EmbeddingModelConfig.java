package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.common.enums.EmbeddingModelEnum;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class EmbeddingModelConfig {

    @Bean
    public EmbeddingModel embeddingModel() {
        OllamaEmbeddingModel embeddingModel = OllamaEmbeddingModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName(EmbeddingModelEnum.EMBEDDINGGEMMA.getCode())
                .build();
        return embeddingModel.addListener(AiListenerConfig.embeddingModelListener());
    }
}
