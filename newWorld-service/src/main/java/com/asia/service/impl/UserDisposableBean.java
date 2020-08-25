package com.asia.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/** spring生命周期 instanceil
 * @author Xavier.liu
 * @date 2020/8/6 14:46
 */
@Service
public class UserDisposableBean implements DisposableBean, InitializingBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("UserDisposableBean destroy=================================>>>>");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserDisposableBean afterPropertiesSet=================================>>>>");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("UserDisposableBean preDestroy=================================>>>>");
    }

    @PostConstruct
    private void postConstruct(){
        System.out.println("UserDisposableBean postConstruct===================================================>>>>>");
    }

    public UserDisposableBean(){
        System.out.println("构造函数 UserDisposableBean()=================================================>>>");
    }
}
