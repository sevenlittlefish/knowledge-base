package com.sevenlittlefish.knowledge.tool;

import com.sevenlittlefish.knowledge.common.constant.VectorCollectionConstants;
import com.sevenlittlefish.knowledge.config.AiListenerConfig;
import com.sevenlittlefish.knowledge.config.EmbeddingStoreConfig;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;

import java.util.Optional;
import java.util.stream.Collectors;

public class RagSearchTool {

    private final EmbeddingModel embeddingModel;

    public RagSearchTool(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Tool("Retrieve knowledge you don't know")
    public String search(String query) {
        String collection = Optional.ofNullable(ReqContext.getCollection()).orElse(VectorCollectionConstants.DEFAULT_COLLECTION);
        EmbeddingStore<TextSegment> embeddingStore = EmbeddingStoreConfig.getStore(collection);

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(5)
                .build();
        contentRetriever = contentRetriever.addListener(AiListenerConfig.contentRetrieverListener());

        // This logic is only executed when the LLM determines retrieval is necessary
        return contentRetriever.retrieve(new Query(query)).stream()
                .map(content -> content.textSegment().text())
                .collect(Collectors.joining("\n\n"));
    }
}
