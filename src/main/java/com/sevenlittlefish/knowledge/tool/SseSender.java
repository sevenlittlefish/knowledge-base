package com.sevenlittlefish.knowledge.tool;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public class SseSender {

    public static void sseSend(SseEmitter emitter, String msg) {
        try {
            emitter.send(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
