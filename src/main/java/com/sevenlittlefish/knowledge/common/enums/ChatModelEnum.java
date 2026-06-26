package com.sevenlittlefish.knowledge.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatModelEnum {

    QWEN3("qwen3:4b", "qwen3 4b"),
    ;

    private final String code;
    private final String desc;
}
