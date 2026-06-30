package com.sevenlittlefish.knowledge.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;

import java.util.List;

public interface DocumentSplitterService {

    List<TextSegment> paragraphSplit(Document document);

    List<TextSegment> paragraphSplit(List<Document> document);
}
