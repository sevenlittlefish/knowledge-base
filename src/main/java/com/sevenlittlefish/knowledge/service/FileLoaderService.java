package com.sevenlittlefish.knowledge.service;

import dev.langchain4j.data.document.Document;

public interface FileLoaderService {

    Document loadFile(String path);
}
