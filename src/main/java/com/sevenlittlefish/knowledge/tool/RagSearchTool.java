package com.sevenlittlefish.knowledge.tool;

import com.sevenlittlefish.knowledge.common.constant.VectorCollectionConstants;
import com.sevenlittlefish.knowledge.config.AiListenerConfig;
import com.sevenlittlefish.knowledge.config.EmbeddingStoreConfig;
import com.sevenlittlefish.knowledge.config.prop.EmbeddingStoreProp;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RagSearchTool {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStoreProp embeddingStoreProp;
    private final ScoringModel scoringModel;

    @Tool("Retrieve knowledge you don't know")
    public String search(String query) {
        String collection = Optional.ofNullable(ReqContext.getCollection()).orElse(VectorCollectionConstants.DEFAULT_COLLECTION);
        EmbeddingStore<TextSegment> embeddingStore = EmbeddingStoreConfig.getStore(embeddingStoreProp, collection);

        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(embeddingModel)
                .embeddingStore(embeddingStore)
                .maxResults(10)
                .build();
        contentRetriever = contentRetriever.addListener(AiListenerConfig.contentRetrieverListener());
        List<Content> retrievedList = contentRetriever.retrieve(new Query(query));

        ReRankingContentAggregator contentAggregator = ReRankingContentAggregator.builder()
                .scoringModel(scoringModel)
                .maxResults(3)
                .build();
        List<Content> aggregatedList = contentAggregator.aggregate(Map.of(new Query(query), Collections.singletonList(retrievedList)));

        return aggregatedList.stream()
                .map(content -> content.textSegment().text())
                .collect(Collectors.joining("\n\n"));
    }
}
