package com.sevenlittlefish.knowledge.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ChatService {

    String chat(String msg);

    SseEmitter chatStream(String msg);
}
