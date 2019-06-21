package cn.mirrorming.blog.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @description:
 * @author: mirrorming
 * @create: 2019-06-16 15:53
 **/
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        log.info("preHandle");

        log.info(((HandlerMethod) handler).getBean().getClass().getName());
        log.info(((HandlerMethod) handler).getMethod().getName());

        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        log.info("[TimeInterceptor] 耗时:" + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        log.info("[TimeInterceptor] 耗时:" + (new Date().getTime() - start));
        log.info("ex is " + e);
    }
}