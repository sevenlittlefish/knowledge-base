package com.sevenlittlefish.knowledge.persistence.repo;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import io.qdrant.client.grpc.Points;

import java.util.List;

public interface StudyNotesRepo {

    void save(List<TextSegment> list);

    List<EmbeddingMatch<TextSegment>> search(String msg);
}
