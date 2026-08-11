package com.sevenlittlefish.knowledge.config;

import com.sevenlittlefish.knowledge.config.prop.EmbeddingStoreProp;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmbeddingStoreConfig {

    public static EmbeddingStore<TextSegment> getStore(EmbeddingStoreProp embeddingStoreProp, String collectionName) {
        QdrantEmbeddingStore embeddingStore = QdrantEmbeddingStore.builder()
                .host(embeddingStoreProp.getHost())
                .port(embeddingStoreProp.getPort())
                .collectionName(collectionName)
                .build();
        return embeddingStore.addListener(AiListenerConfig.embeddingStoreListener());
    }
}
