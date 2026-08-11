package com.sevenlittlefish.knowledge.common;

import com.sevenlittlefish.knowledge.common.constant.CommonConstants;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;

public class DocumentSplitterSet {

    public static final DocumentByParagraphSplitter PARAGRAPH_SPLITTER = new DocumentByParagraphSplitter(CommonConstants.MAX_SEGMENT_SIZE_IN_CHARS, CommonConstants.MAX_OVERLAP_SIZE_IN_CHARS);
}
