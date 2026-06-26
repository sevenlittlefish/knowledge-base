package com.sevenlittlefish.knowledge.config;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import dev.langchain4j.model.embedding.listener.EmbeddingModelErrorContext;
import dev.langchain4j.model.embedding.listener.EmbeddingModelListener;
import dev.langchain4j.model.embedding.listener.EmbeddingModelRequestContext;
import dev.langchain4j.model.embedding.listener.EmbeddingModelResponseContext;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverErrorContext;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverListener;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverRequestContext;
import dev.langchain4j.rag.content.retriever.listener.ContentRetrieverResponseContext;
import dev.langchain4j.store.embedding.listener.EmbeddingStoreErrorContext;
import dev.langchain4j.store.embedding.listener.EmbeddingStoreListener;
import dev.langchain4j.store.embedding.listener.EmbeddingStoreRequestContext;
import dev.langchain4j.store.embedding.listener.EmbeddingStoreResponseContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AiListenerConfig {

    public static ChatModelListener chatModelListener() {
        return new ChatModelListener() {

            @Override
            public void onRequest(ChatModelRequestContext requestContext) {
                log.info("onRequest(): {}", requestContext.chatRequest());
            }

            @Override
            public void onResponse(ChatModelResponseContext responseContext) {
                log.info("onResponse(): {}", responseContext.chatResponse());
            }

            @Override
            public void onError(ChatModelErrorContext errorContext) {
                log.info("onError(): {}", errorContext.error().getMessage());
            }
        };
    }

    public static EmbeddingModelListener embeddingModelListener() {
        return new EmbeddingModelListener() {

            @Override
            public void onRequest(EmbeddingModelRequestContext requestContext) {
                requestContext.attributes().put("startTime", System.currentTimeMillis());
            }

            @Override
            public void onResponse(EmbeddingModelResponseContext responseContext) {
                long startTime = (long) responseContext.attributes().get("startTime");
                long duration = System.currentTimeMillis() - startTime;
                log.info("Embedding model duration: {}ms", duration);
            }

            @Override
            public void onError(EmbeddingModelErrorContext errorContext) {
                Throwable error = errorContext.error();
                log.info("Embedding model error: {}", error.getMessage());
            }
        };
    }

    public static EmbeddingStoreListener embeddingStoreListener() {
        return new EmbeddingStoreListener() {

            @Override
            public void onRequest(EmbeddingStoreRequestContext<?> requestContext) {
                requestContext.attributes().put("startTime", System.currentTimeMillis());
            }

            @Override
            public void onResponse(EmbeddingStoreResponseContext<?> responseContext) {
                long startTime = (long) responseContext.attributes().get("startTime");
                long duration = System.currentTimeMillis() - startTime;
                log.info("Embedding store duration: {}ms", duration);
            }

            @Override
            public void onError(EmbeddingStoreErrorContext<?> errorContext) {
                Throwable error = errorContext.error();
                log.info("Embedding store error: {}", error.getMessage());
            }
        };
    }

    public static ContentRetrieverListener contentRetrieverListener() {
        return new ContentRetrieverListener() {

            @Override
            public void onRequest(ContentRetrieverRequestContext requestContext) {
                requestContext.attributes().put("startTime", System.currentTimeMillis());
            }

            @Override
            public void onResponse(ContentRetrieverResponseContext responseContext) {
                long startTime = (long) responseContext.attributes().get("startTime");
                long duration = System.currentTimeMillis() - startTime;
                log.info("Content retriever duration: {}ms", duration);
            }

            @Override
            public void onError(ContentRetrieverErrorContext errorContext) {
                Throwable error = errorContext.error();
                log.info("Content retriever error: {}", error.getMessage());
            }
        };
    }
}
