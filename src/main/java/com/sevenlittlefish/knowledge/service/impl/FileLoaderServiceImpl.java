package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.service.FileLoaderService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileLoaderServiceImpl implements FileLoaderService {

    @Override
    public Document loadFile(String path) {
        return FileSystemDocumentLoader.loadDocument(path, new ApacheTikaDocumentParser());
    }

    @Override
    public List<Document> loadDirectory(String path) {
        return FileSystemDocumentLoader.loadDocuments(path, new ApacheTikaDocumentParser());
    }
}
