package asynctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Xavier.liu
 * @date 2020/7/10 11:40
 */
@SpringBootApplication
@EnableAsync
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
