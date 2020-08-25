package com.asia.test;

import com.asia.ConvertByteObject;
import com.asia.annotation.TimeXavierLog;
import com.asia.data.UserXavier;
import com.asia.mq.Message;
import com.asia.mq.Producer;
import com.asia.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.beans.Encoder;
import java.util.Base64;

/**
 * @author Xavier.liu
 * @date 2020/5/14 11:59
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private Producer producer;

    @Autowired
    private UserService userService;

    @RequestMapping("sendMessage")
    public String sendMessage(){
        for (int i = 0; i < 100; i++){
            Message message = Message.builder()
                    .msgSeq(i)
                    .msgBody("Message RmqEncapsulation" + i)
                    .build();
            byte[] body = ConvertByteObject.getByteFromObject(message);
            producer.send("asia.liu.exchange","rk", body);
        }
        return "success";
    }

    @RequestMapping("mockRequest")
    @TimeXavierLog(10)
    public String mockRequest(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     *  consumes = "application/json"
     *      -- @RequestBody 参数绑定
     *
     * @param id
     * @return
     */
    @PostMapping(value = "getUser")
    public UserXavier getUser(Long id){
        return userService.getUser(id);
    }

    @PostMapping(value = "addUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserXavier userXavier){
        userService.setUser(userXavier);
    }

    @GetMapping("testTransactionWithAsync")
    @Transactional(rollbackFor = Exception.class)
    public void testTransactionWithAsync(){
        log.info("testTransactionWithAsync start");
        userService.remoteProcedureCall();
        log.info("testTransactionWithAsync end-------");

    }
}
