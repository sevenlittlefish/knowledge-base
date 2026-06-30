package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.domain.LoadFileDto;
import com.sevenlittlefish.knowledge.persistence.repo.EmbeddingStoreRepo;
import com.sevenlittlefish.knowledge.service.DocumentSplitterService;
import com.sevenlittlefish.knowledge.service.FileLoaderService;
import com.sevenlittlefish.knowledge.service.EmbeddingStoreService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class EmbeddingStoreServiceImpl implements EmbeddingStoreService {

    @Resource
    private FileLoaderService fileLoaderService;
    @Resource
    private DocumentSplitterService documentSplitterService;
    @Resource
    private EmbeddingStoreRepo embeddingStoreRepo;

    @Override
    public void loadFile(LoadFileDto dto) {
        Path path = Paths.get(dto.getPath());
        if (!Files.exists(path)) {
            throw new RuntimeException("file or directory not found");
        }

        List<TextSegment> textSegments;

        if (dto.isDirectory()) {
            List<Document> documents = fileLoaderService.loadDirectory(dto.getPath());
            textSegments = documentSplitterService.paragraphSplit(documents);
        } else {
            Document document = fileLoaderService.loadFile(dto.getPath());
            textSegments = documentSplitterService.paragraphSplit(document);
        }

        embeddingStoreRepo.save(textSegments);
    }
}
