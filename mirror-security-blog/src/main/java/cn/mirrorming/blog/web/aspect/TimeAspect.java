package cn.mirrorming.blog.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: mirrorming
 * @create: 2019-06-16 16:05
 **/
@Aspect
@Component
@Slf4j
public class TimeAspect {
    @Around("execution(* cn.mirrorming.blog.web.controller.DemoController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        log.info("[TimeAspect] time aspect start");

        Object[] args = point.getArgs();
        for (Object arg : args) {
            log.info("arg is " + arg);
        }

        long start = new Date().getTime();

        Object object = point.proceed();

        log.info("[TimeAspect] time aspect 耗时:" + (new Date().getTime() - start));

        log.info("[TimeAspect] time aspect end");

        return object;
    }
}