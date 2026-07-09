package com.sevenlittlefish.knowledge.service;

import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    String chat(String userMsg);

    String chat(@MemoryId int memoryId, @UserMessage String userMsg);

    String chat(@UserMessage String userMessage, ChatRequestParameters params);

    String chat(@MemoryId int memoryId, @UserMessage String userMessage, ChatRequestParameters params);

    TokenStream chatStream(String message);

    TokenStream chatStream(@MemoryId int memoryId, @UserMessage String message);
}
