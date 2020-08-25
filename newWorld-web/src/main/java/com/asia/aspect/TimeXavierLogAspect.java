package com.asia.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import com.asia.annotation.TimeXavierLog;
import org.springframework.stereotype.Component;


/**
 * @author Xavier.liu
 * @date 2020/6/2 16:01
 */
@Aspect
@Slf4j
@Component
public class TimeXavierLogAspect {

    @Around(value = "within(com.asia..*) && @annotation(timeXavierLog)")
    public Object timeOutLog(ProceedingJoinPoint proceedingJoinPoint, TimeXavierLog timeXavierLog){
        long start = System.currentTimeMillis();
        Object re = null;
        try {
            re = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("proceed error");
        }
        long costTime = System.currentTimeMillis() - start;
        log.info("request costTime:{},timeXavierLog value:{}",costTime,timeXavierLog.value());
        if (costTime > timeXavierLog.value()){
            log.info("request lone time spend:{}ms", costTime);
        }
        return re;
    }
}
