package com.sinosoft.cisfrm.server.interceptor;

import com.sinosoft.cisfrm.server.annotation.Interceptor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dawn on 16/2/25.
 */
@Interceptor
public class TestInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LogManager.getLogger(TestInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        return true;
    }

}
