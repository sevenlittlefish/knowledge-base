package com.sevenlittlefish.knowledge.service;

import dev.langchain4j.data.document.Document;

import java.util.List;

public interface FileLoaderService {

    Document loadFile(String path);

    List<Document> loadDirectory(String path);
}
