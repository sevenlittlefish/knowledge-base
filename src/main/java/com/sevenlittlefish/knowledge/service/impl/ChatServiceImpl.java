package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.common.constant.VectorCollectionConstants;
import com.sevenlittlefish.knowledge.config.AiListenerConfig;
import com.sevenlittlefish.knowledge.config.EmbeddingStoreConfig;
import com.sevenlittlefish.knowledge.service.Assistant;
import com.sevenlittlefish.knowledge.service.ChatService;
import com.sevenlittlefish.knowledge.tool.RagSearchTool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private Assistant assistant;

    @Resource
    private ChatModel chatModel;
    @Resource
    private EmbeddingStoreConfig embeddingStoreConfig;
    @Resource
    private EmbeddingModel embeddingModel;

    @Override
    public String chat(String msg) {
        EmbeddingStore<TextSegment> embeddingStore = embeddingStoreConfig.getStore(VectorCollectionConstants.DEFAULT_COLLECTION);
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(5)
                .build();
        contentRetriever = contentRetriever.addListener(AiListenerConfig.contentRetrieverListener());
        /*
         * # Directly referencing Rag
         * 1、Assistant assistant = AiServices.builder(Assistant.class).chatModel(chatModel).contentRetriever(contentRetriever).build();
         * 2、DefaultRetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder().contentRetriever(contentRetriever).build();
         *    Assistant assistant = AiServices.builder(Assistant.class).chatModel(chatModel).retrievalAugmentor(retrievalAugmentor).build();
         */
        synchronized (this) {
            if (assistant == null) {
                assistant = AiServices.builder(Assistant.class)
                        .chatModel(chatModel)
                        .tools(new RagSearchTool(contentRetriever))
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                        .build();
            }
        }
        return assistant.chat(msg);
    }
}
