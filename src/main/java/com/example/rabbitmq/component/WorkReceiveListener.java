package com.example.rabbitmq.component;

import com.example.rabbitmq.model.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

// 消费者
@Component
public class WorkReceiveListener {
    //任务模型 消费者1
    @RabbitListener(queues = "queue_work")
    public void receiveMessage(String msg, Channel channel, Message message) {
        // 只包含发送的消息
        System.out.println("1接收到消息：" + msg);
        // channel 通道信息
        // message 附加的参数信息
    }

    //任务模型 消费者2
    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj, Channel channel, Message message) {
        // 包含所有的信息
        System.out.println("2接收到消息：" + obj + message);
    }

    @RabbitListener(queues = "order.ensure")
    public void ensureOrder(User user, Channel channel, Message message){
        System.out.println("接收信息成功1！ "+"用户："+ user +" 订单信息："+user.getPassword());
    }

    @RabbitListener(queues = "order.ensure")
    public void ensureOrder2(User user, Channel channel, Message message){
        System.out.println("接收信息成功2！ "+"用户："+ user +" 订单信息："+user.getPassword());
    }

    //fount 广播 消费者1
    @RabbitListener(
            bindings = {@QueueBinding(
                    value = @Queue, //临时队列
                    exchange = @Exchange(value = "logs",type = "fanout")//指定交换机和交换机类型
            )})
    public void consumer1(String message){
        System.out.println("======Consumer1:"+message);
    }

    //fount 广播 消费者2
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //临时队列
                    exchange = @Exchange(value = "logs",type = "fanout") //指定交换机和交换机类型
            )})
    public void consumer2(String message){
        System.out.println("======Consumer2:"+message);
    }

    //Routing-Direct直连
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  //临时队列
                    exchange = @Exchange(value = "directs",type = "direct"), //交换机名称和类型
                    key = {"info","error","warning"}
            )})
    public void routingDirect1(String message){
        System.out.println("======Consumer1:"+message);
    }

    //Routing-Direct直连
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,  //临时队列
                    exchange = @Exchange(value = "directs",type = "direct"), //交换机名称和类型
                    key = {"error"}
            )})
    public void routingDirect2(String message){
        System.out.println("======Consumer2:"+message);
    }

    //Routing-Topics动态路由
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topics",type = "topic"),
                    key = {"user.*"}
            )
    })
    public void routingTopic1(String message){
        System.out.println("======Consumer1:"+message);
    }

    //Routing-Topics动态路由
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topics",type = "topic"),
                    key = {"user.#","order.#"}
            )
    })
    public void routingTopic2(String message){
        System.out.println("======Consumer2:"+message);
    }
}

