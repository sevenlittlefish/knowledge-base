package com.sevenlittlefish.knowledge.service;

import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    String chat(@UserMessage String userMsg);

    String chat(@UserMessage String userMessage, ChatRequestParameters params);
}
