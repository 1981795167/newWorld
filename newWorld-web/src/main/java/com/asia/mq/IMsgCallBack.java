package com.asia.mq;

/**
 *    //回调函数 --- 被传入的，后再调用的函数
 *    1.方便在匿名类实现自己的方法；
 *    2.定义一个new Thread ，然后在run方法里实现逻辑也是一种回调
 *
 *    a.func(CallBack());
 *
 *    class a{
 *        String param = "liu";
 *        public T func(CallBack cb){
 *            cb.process(param);
 *        }
 *    }
 *
 *    interface CallBack{
 *        T process(String param);
 *    }
 *
 * @author Xavier.liu
 * @date 2020/5/14 18:16
 */
public interface IMsgCallBack {

    String consumeMsg(Message message);
}
