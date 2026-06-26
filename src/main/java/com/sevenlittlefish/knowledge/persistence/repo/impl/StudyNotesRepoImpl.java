package com.sevenlittlefish.knowledge.persistence.repo.impl;

import com.sevenlittlefish.knowledge.common.constant.VectorCollectionConstants;
import com.sevenlittlefish.knowledge.config.EmbeddingStoreConfig;
import com.sevenlittlefish.knowledge.persistence.repo.StudyNotesRepo;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudyNotesRepoImpl implements StudyNotesRepo {

    @Resource
    private EmbeddingStoreConfig embeddingStoreConfig;
    @Resource
    private EmbeddingModel embeddingModel;

    @Override
    public void save(List<TextSegment> list) {
        EmbeddingStore<TextSegment> embeddingStore = embeddingStoreConfig.getStore(VectorCollectionConstants.STUDY_NOTE_COLLECTION);
        for (TextSegment segment : list) {
            Embedding embedding = embeddingModel.embed(segment).content();
            embeddingStore.add(embedding, segment);
        }
    }

    @Override
    public List<EmbeddingMatch<TextSegment>> search(String msg) {
        Embedding embedding = embeddingModel.embed(msg).content();
        EmbeddingSearchRequest request = EmbeddingSearchRequest.builder().queryEmbedding(embedding).build();

        EmbeddingStore<TextSegment> embeddingStore = embeddingStoreConfig.getStore(VectorCollectionConstants.STUDY_NOTE_COLLECTION);
        return embeddingStore.search(request).matches();
    }
}
