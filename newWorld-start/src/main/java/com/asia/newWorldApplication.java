package com.asia;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Xavier.liu
 * @date 2020/5/14 12:02
 */
@SpringBootApplication
@Slf4j
@EnableAsync
public class newWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(newWorldApplication.class,args);
        log.info("--------new-World is started------");
    }
}
