package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.common.DocumentSplitterSet;
import com.sevenlittlefish.knowledge.service.DocumentSplitterService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentSplitterServiceImpl implements DocumentSplitterService {

    @Override
    public List<TextSegment> paragraphSplit(Document document) {
        return DocumentSplitterSet.PARAGRAPH_SPLITTER.split(document);
    }
}
