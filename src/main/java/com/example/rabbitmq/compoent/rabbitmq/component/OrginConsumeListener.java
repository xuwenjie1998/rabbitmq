package com.example.rabbitmq.compoent.rabbitmq.component;

import com.example.rabbitmq.compoent.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class OrginConsumeListener {
    public static void main(String[] args) throws Exception{

        Connection connect = ConnectionUtils.getConnect();
        Channel channel = connect.createChannel();
        channel.queueDeclare("orginQueue",false,false,false,null);
        //订阅消息
        channel.basicConsume("orginQueue",true,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("recv[confirm] msg : " + new String(body, "utf-8"));
            }
        });

//        channel.close();
//        connect.close();
    }
}
