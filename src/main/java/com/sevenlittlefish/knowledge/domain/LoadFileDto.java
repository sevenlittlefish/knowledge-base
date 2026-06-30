package com.sevenlittlefish.knowledge.domain;

import lombok.Data;

@Data
public class LoadFileDto {
    private String path;
    private boolean directory;
}
