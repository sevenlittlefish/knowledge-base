package com.sevenlittlefish.knowledge.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmbeddingModelEnum {

    EMBEDDINGGEMMA("embeddinggemma:latest", "gemma embedding model"),
    ;

    private final String code;
    private final String desc;
}
