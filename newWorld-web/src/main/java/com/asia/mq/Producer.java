package com.asia.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ReturnListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

 /**
 * @author Xavier.liu
 * @date 2020/5/14 15:52
 */
@Slf4j
@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Integer subDivisionNum;

//    public Producer(Integer subDivisionNum){
//        this.subDivisionNum = subDivisionNum;
//    }

    /**
     *
     * @param channel
     * @param exchange
     * @param routingKey
     * @param mandatory 强制的：true，交换器无法根据自身type和routingKey 找到队列时，mq会调用basic.Return 将消息返回给生产者。
     *                  false： 直接丢弃
     * @param immediate true：路由匹配的所有队列上没有 消费者，返回给生产者
     *                  mq3.0版本已不使用；--影响镜像队列性能，建议用 TTL，DLX替代
     *
     *        TTL: time to live
     *             AMQP.BasicProperties.expiration() 过期时间
     *             不设置不过期。0：表示如果没有消费者，丢弃 -- 对应immediate = false
     *        DLX：dead letter exchange  与之绑定的就是死信队列
     *             1.消息被拒绝（basic.reject/basic.nack）,且 requeue 参数 true
     *             2.消息过期
     *             3.队列达到最大长度
     * @param props 消息的基本属性级
     * @param body
     * @throws IOException
     */
    public void basicPublish(Channel channel, String exchange, String routingKey,
                             boolean mandatory, boolean immediate, AMQP.BasicProperties props, byte[] body) throws IOException {
        int index = new Random().nextInt(subDivisionNum);
        channel.basicPublish(exchange, routingKey + "#" + index, mandatory, immediate, props, body);
        if (mandatory){
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange,
                                         String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                         log.info("basic.Return body:{}",body);
                }
            });
        }
    }

    public void send(String exchange, String routingKey, byte[] message){
//        int index = new Random().nextInt(subDivisionNum);
        rabbitTemplate.convertAndSend(exchange,routingKey ,message);
    }
}
