package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.service.FileLoaderService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import org.springframework.stereotype.Service;

@Service
public class FileLoaderServiceImpl implements FileLoaderService {

    @Override
    public Document loadFile(String path) {
        return FileSystemDocumentLoader.loadDocument(path, new TextDocumentParser());
    }
}
