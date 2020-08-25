package com.asia.mq;

import com.asia.ConvertByteObject;
import com.rabbitmq.client.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author Xavier.liu
 * @date 2020/5/14 17:31
 */
@Component
@RabbitListener(queues = "asia.liu.queue")
public class Consumer {

    @Autowired
    private Environment environment;

    private static int subDivisionNum = 4;

    public GetResponse basicGet(Channel channel, String queue, boolean autoAck) throws IOException {
        int index = new Random().nextInt(subDivisionNum);

        GetResponse getResponse;
        getResponse = channel.basicGet(queue + index, autoAck);
        if (getResponse == null){
            for (int i = 0; i < subDivisionNum; i++) {
                getResponse = channel.basicGet(queue + index, autoAck);
                if (getResponse != null)
                    break;
            }
        }
        return getResponse;
    }

    public void basicConsume(Channel channel, String queue, boolean autoAck,
                             String consumerTag, ConcurrentLinkedDeque<Message> newBlockingQueue, IMsgCallBack iMsgCallBack) throws IOException {
        startConsume(channel, queue, autoAck, consumerTag, newBlockingQueue);
        while (true){
            Message message = newBlockingQueue.peekFirst();
            if (message != null){
                String consumeStatus = iMsgCallBack.consumeMsg(message);
                newBlockingQueue.removeFirst();
                if ("SUCCESS".equals(consumeStatus)){

                    /**
                     *  basicAck
                     *      multiple: true: 表示到这个deliverTag之前的所有消息 都已经处理过
                     *                false: 只通过了这个 序号消息
                     */
                    channel.basicAck(message.getDeliverTag(), false);
                }else {
                    channel.basicReject(message.getDeliverTag(), false);
                }
            }else {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param channel
     * @param queue
     * @param autoAck
     * @param consumerTag 不同的订阅采用不同的消费者标签区分，用来区分多个消费者
     * @param newBlockingQueue
     * @throws IOException
     */
    private void startConsume(Channel channel, String queue, boolean autoAck,
                              String consumerTag, ConcurrentLinkedDeque<Message> newBlockingQueue) throws IOException {
        for (int i = 0; i < subDivisionNum; i++) {
            channel.basicConsume(queue + "#" + i, autoAck, consumerTag + i,
                    new NewConsumer(channel, newBlockingQueue));
        }
    }

    static class NewConsumer extends DefaultConsumer{
        private ConcurrentLinkedDeque<Message> newBlockingQueue;

        public NewConsumer(Channel channel, ConcurrentLinkedDeque<Message> newBlockingQueue) {
            super(channel);
            this.newBlockingQueue = newBlockingQueue;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
            Message message = (Message) ConvertByteObject.getObjectFromByte(body);

            /**
             * deliverTag: 消息的有序编号
             *              在发送者确认机制下，mq回传给生产者的确认消息 包含deliverTag
             */
            message.setDeliverTag(envelope.getDeliveryTag());
            newBlockingQueue.addLast(message);
        }
    }

    @RabbitHandler
    public void consume(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag, String message) throws IOException {
        try {
            System.out.println(tag + "consume@@" + message + Arrays.toString(environment.getActiveProfiles()));
            channel.basicAck(tag,false);
        } catch (IOException e) {
            e.printStackTrace();
            channel.basicReject(tag,false);
        }
    }
}
