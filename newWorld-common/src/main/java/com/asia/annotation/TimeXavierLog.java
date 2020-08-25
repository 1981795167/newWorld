package com.asia.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xavier.liu
 * @date 2020/6/2 15:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TimeXavierLog {

    /**
     * 方法执行超过时间 打印日志
     * @return default 3000
     */
    long value() default 3000;
}
