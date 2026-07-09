package com.sevenlittlefish.knowledge.service.impl;

import com.sevenlittlefish.knowledge.common.constant.CommonConstants;
import com.sevenlittlefish.knowledge.common.constant.VectorCollectionConstants;
import com.sevenlittlefish.knowledge.tool.SseSender;
import com.sevenlittlefish.knowledge.service.Assistant;
import com.sevenlittlefish.knowledge.service.ChatService;
import com.sevenlittlefish.knowledge.tool.ReqContext;
import dev.langchain4j.service.TokenStream;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private Assistant assistant;

    @Override
    public String chat(String msg) {
        ReqContext.setCollection(VectorCollectionConstants.DEFAULT_COLLECTION);
        return assistant.chat(ReqContext.getMemory(), msg);
    }

    @Override
    public SseEmitter chatStream(String msg) {
        SseEmitter emitter = new SseEmitter(CommonConstants.CHAT_MODEL_TIMEOUT);
        ReqContext.setCollection(VectorCollectionConstants.DEFAULT_COLLECTION);
        TokenStream tokenStream = assistant.chatStream(ReqContext.getMemory(), msg);
        tokenStream
                .onPartialResponse(s -> SseSender.sseSend(emitter, s))
                .onPartialThinking(partialThinking -> SseSender.sseSend(emitter, partialThinking.toString()))
                .onIntermediateResponse(chatResponse -> SseSender.sseSend(emitter, chatResponse.toString()))
                .onPartialToolCall(partialToolCall -> SseSender.sseSend(emitter, partialToolCall.toString()))
                .beforeToolExecution(beforeToolExecution -> SseSender.sseSend(emitter, beforeToolExecution.toString()))
                .onToolExecuted(toolExecution -> SseSender.sseSend(emitter, toolExecution.toString()))
                .onCompleteResponse(chatResponse -> emitter.complete())
                .onError(emitter::completeWithError)
                .start();
        return emitter;
    }
}
