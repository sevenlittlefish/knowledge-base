package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.service.Assistant;
import com.sevenlittlefish.knowledge.tool.RagSearchTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig {

    @Resource
    private ChatModel chatModel;
    @Resource
    private StreamingChatModel streamingChatModel;
    @Resource
    private EmbeddingModel embeddingModel;

    @Bean
    public Assistant assistant() {
        return AiServices.builder(Assistant.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .tools(new RagSearchTool(embeddingModel))
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }
}
