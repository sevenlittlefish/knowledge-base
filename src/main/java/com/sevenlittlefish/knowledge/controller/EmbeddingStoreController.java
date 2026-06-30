package com.sevenlittlefish.knowledge.controller;

import com.sevenlittlefish.knowledge.domain.LoadFileDto;
import com.sevenlittlefish.knowledge.service.EmbeddingStoreService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("embeddingStore")
public class EmbeddingStoreController {

    @Resource
    private EmbeddingStoreService embeddingStoreService;

    @PostMapping("loadFile")
    public void loadFile(@RequestBody LoadFileDto dto) {
        embeddingStoreService.loadFile(dto);
    }
}
