package com.sevenlittlefish.knowledge.controller;

import com.sevenlittlefish.knowledge.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class ChatController {

    @Resource
    private ChatService chatService;

    @GetMapping("chat")
    public String chat(String msg) {
        return chatService.chat(msg);
    }

    @GetMapping(path = "chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chatStream(String msg) {
        return chatService.chatStream(msg);
    }
}
