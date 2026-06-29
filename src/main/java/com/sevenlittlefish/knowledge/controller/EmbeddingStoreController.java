package com.sevenlittlefish.knowledge.controller;

import com.sevenlittlefish.knowledge.service.EmbeddingStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("embeddingStore")
public class EmbeddingStoreController {

    @Resource
    private EmbeddingStoreService embeddingStoreService;

    @PostMapping("loadFile")
    public void loadFile(String filePath) {
        embeddingStoreService.loadFile(filePath);
    }
}
