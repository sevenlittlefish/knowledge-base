package com.sevenlittlefish.knowledge.common;

import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;

public class DocumentSplitterSet {

    public static final DocumentByParagraphSplitter PARAGRAPH_SPLITTER = new DocumentByParagraphSplitter(512, 128);
}
