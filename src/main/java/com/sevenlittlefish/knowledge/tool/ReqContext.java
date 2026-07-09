package com.sevenlittlefish.knowledge.tool;

public class ReqContext {

    private static final ThreadLocal<Integer> MEMORY_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> COLLECTION_THREAD_LOCAL = new ThreadLocal<>();

    public static void setMemory(Integer id) {
        MEMORY_THREAD_LOCAL.set(id);
    }

    public static Integer getMemory() {
        return MEMORY_THREAD_LOCAL.get();
    }

    public static void clearMemory() {
        MEMORY_THREAD_LOCAL.remove();
    }

    public static void setCollection(String collection) {
        COLLECTION_THREAD_LOCAL.set(collection);
    }

    public static String getCollection() {
        return COLLECTION_THREAD_LOCAL.get();
    }

    public static void clearCollection() {
        COLLECTION_THREAD_LOCAL.remove();
    }
}
