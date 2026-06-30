package com.sevenlittlefish.knowledge.service;

import com.sevenlittlefish.knowledge.domain.LoadFileDto;

public interface EmbeddingStoreService {

    void loadFile(LoadFileDto dto);
}
