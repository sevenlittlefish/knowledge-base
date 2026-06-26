package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.common.enums.ChatModelEnum;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class ChatModelConfig {

    @Bean
    public ChatModel chatModel() {
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName(ChatModelEnum.QWEN3.getCode())
                .listeners(List.of(AiListenerConfig.chatModelListener()))
                .timeout(Duration.ofSeconds(1800))
                .build();
    }
}
