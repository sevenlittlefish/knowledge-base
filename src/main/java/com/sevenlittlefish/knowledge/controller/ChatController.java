package com.sevenlittlefish.knowledge.controller;

import com.sevenlittlefish.knowledge.service.ChatService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Resource
    private ChatService chatService;

    @PostMapping("chat")
    public String chat(String msg) {
        return chatService.chat(msg);
    }
}
