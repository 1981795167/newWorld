package com.asia.mq;

import com.asia.ConvertByteObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeoutException;

/**
 * @author Xavier.liu
 * @date 2020/5/14 16:18
 */
public class MqSendMessageRun {



    //------可优化：

    //顺序性
    public static void main(String[] args) {
        RmqEncapsulation rmqEncapsulation = new RmqEncapsulation(4);
        try {
            Connection connection = RmqEncapsulation.getConnection();
            Channel channel = connection.createChannel();
            rmqEncapsulation.exchangeDeclare(channel, "exchange", "direct",
                    true,false, false, null);
            rmqEncapsulation.queueDeclare(channel, "queue", true, false, false, null);
            rmqEncapsulation.queueBind(channel, "exchange", "queue", "rk", null);

            for (int i = 0; i < 10; i++){
                Message message = Message.builder()
                        .msgSeq(i)
                        .msgBody("Message RmqEncapsulation")
                        .build();
                byte[] body = ConvertByteObject.getByteFromObject(message);
//                new Producer(4).basicPublish(channel,"exchange","rk",
//                        false ,false, MessageProperties.PERSISTENT_TEXT_PLAIN, body);
            }

            //consume ========================



            /**
             * 一般是轮询到所有消费者上： 导致有的消费者任务繁重来不及消费，有的消费者（业务逻辑简单，机器性能卓越）的问题？
             * 解决方法：
             * basicQos：允许限制信道上的消费者 所能保持的最大未确定消息数（滑动窗口）
             * 维护一个消费者列表
             *
             */
            channel.basicQos(64);
            new Consumer().basicConsume(channel,"queue",false,"consumer-lxf", new ConcurrentLinkedDeque<>(),
                    new IMsgCallBack(){
                        @Override
                        public String consumeMsg(Message message) {
                            if (message != null){
                                System.out.println(message);
                                return "SUCCESS";
                            }
                            return "FAIL";
                        }
                    });

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }finally {
            try {
                RmqEncapsulation.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
