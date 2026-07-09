package com.sevenlittlefish.knowledge.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmbeddingStoreConfig {

    public static EmbeddingStore<TextSegment> getStore(String collectionName) {
        QdrantEmbeddingStore embeddingStore = QdrantEmbeddingStore.builder()
                .host("localhost")
                .port(6334)
                .collectionName(collectionName)
                .build();
        return embeddingStore.addListener(AiListenerConfig.embeddingStoreListener());
    }
}
