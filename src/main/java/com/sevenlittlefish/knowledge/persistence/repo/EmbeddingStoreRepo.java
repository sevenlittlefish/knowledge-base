package com.sevenlittlefish.knowledge.persistence.repo;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;

import java.util.List;

public interface EmbeddingStoreRepo {

    void save(List<TextSegment> list);

    List<EmbeddingMatch<TextSegment>> search(String msg);
}
