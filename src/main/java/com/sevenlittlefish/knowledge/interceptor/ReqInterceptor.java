package com.sevenlittlefish.knowledge.interceptor;

import com.sevenlittlefish.knowledge.tool.ReqContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.math.NumberUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

public class ReqInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer mid = NumberUtils.toInt(request.getHeader("mid"));
        ReqContext.setMemory(mid);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        ReqContext.clearMemory();
        ReqContext.clearCollection();
    }
}
