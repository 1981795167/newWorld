package com.asia.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 打破队列瓶颈- 封装多个队列
 * @author Xavier.liu
 * @date 2020/5/14 15:06
 */
public class RmqEncapsulation {
    private static String host = "127.0.0.1";
    private static String vhost = "/";
    /**
     *     server: 5672 ; 监控页面： 15672
     */
    private static Integer port = 5672;
    private static String userName = "guest";
    private static String password = "guest";

    private static Connection connection;

    /**
     *     分片数，表示一个逻辑队列背后实际的队列数
     */
    private  Integer subDivisionNum ;

    public static void newConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setVirtualHost(vhost);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connection = connectionFactory.newConnection();
    }

    public RmqEncapsulation(Integer subDivisionNum){
        this.subDivisionNum = subDivisionNum;
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        if (connection == null)
            newConnection();
        return connection;
    }

    public static void closeConnection() throws IOException {
        if (connection != null)
            connection.close();
    }

    /**
     *
     * @param channel
     * @param exchange
     * @param type
     *          fanout 路由到所有队列；
     *          direct bindingKey 和 routingKey 完全匹配 队列；
     *          topic  模糊匹配
     *                  * ：匹配一个单词
     *                  #：多个单词
     *          headers 依赖消息内容的header - 键值对；
     * @param durable 持久化；交换器存盘，broker重启不会丢失交换器基本信息
     * @param autoDelete 自动删除：至少有一个队列或交换器与 这个交换器绑定之后，所有的队列或交换器都解绑了，该交换器自动删除
     *                             （不再使用）
     *                              当客户端创建这个交换器时，从来没有连接时是不会自动删除的。
     * @param internal 内置：内置交换器 客户端不能直接发送消息到这，只能通过 交换器路由到交换器
     * @param args
     * @throws IOException
     */
    public void exchangeDeclare(Channel channel, String exchange, String type,
                                boolean durable,  boolean autoDelete, boolean internal, Map<String,Object> args) throws IOException {
        channel.exchangeDeclare(exchange, type, durable, autoDelete, internal, args);
    }

    /**
     *
     * @param channel
     * @param queue
     * @param durable 同上；
     *                注意：队列没持久化，消息持久化属性是没用的，载体都没了，消息何去何从？
     * @param exclusive 独占，排他：
     *                      首次申明它的连接可见， 断开后自动删除
     *                  适用于一个客户端 同时发送和读取消息
     * @param autoDelete 同上；
     * @param args
     * @throws IOException
     */
    public void queueDeclare(Channel channel, String queue, boolean durable,
                             boolean exclusive, boolean autoDelete, Map<String,Object> args) throws IOException {
        for (int i = 0; i < subDivisionNum; i++) {
            String queueName = queue + "#" + i;
            channel.queueDeclare(queueName, durable, exclusive, autoDelete, args);
        }
    }

    public void queueBind(Channel channel, String exchange, String queue,
                          String routingKey, Map<String,Object> args) throws IOException {
        for (int i = 0; i < subDivisionNum; i++) {
            String queueName = queue + "#" + i;
            String rtKey = routingKey + "#" + i;
            channel.queueBind(queueName, exchange, rtKey, args);
        }
    }
}
