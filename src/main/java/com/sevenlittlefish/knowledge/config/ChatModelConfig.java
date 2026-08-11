package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.common.constant.CommonConstants;
import com.sevenlittlefish.knowledge.common.enums.ChatModelEnum;
import com.sevenlittlefish.knowledge.config.prop.ChatModelProp;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class ChatModelConfig {

    @Resource
    private ChatModelProp chatModelProp;

    @Bean
    public ChatModel chatModel() {
        return OllamaChatModel.builder()
                .baseUrl(chatModelProp.getUrl())
                .modelName(ChatModelEnum.QWEN3.getCode())
                .listeners(List.of(AiListenerConfig.chatModelListener()))
                .timeout(Duration.ofMillis(CommonConstants.CHAT_MODEL_TIMEOUT_MILLIS))
                .build();
    }

    @Bean
    public StreamingChatModel streamingChatModel() {
        return OllamaStreamingChatModel.builder()
                .baseUrl(chatModelProp.getUrl())
                .modelName(ChatModelEnum.QWEN3.getCode())
                .listeners(List.of(AiListenerConfig.chatModelListener()))
                .timeout(Duration.ofMillis(CommonConstants.CHAT_MODEL_TIMEOUT_MILLIS))
                .build();
    }
}
