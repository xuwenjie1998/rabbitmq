package com.example.rabbitmq.compoent.rabbitmq.controller;

import com.example.rabbitmq.compoent.rabbitmq.model.User;
import com.example.rabbitmq.compoent.rabbitmq.service.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//服务者
@RestController
public class RabbitmqController {

    @Autowired
    private RabbitmqService rabbitmqService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendWork")
    public Object sendWork() {
        rabbitmqService.sendWork();
        return "发送成功...";
    }

    @RequestMapping("/sendOrder")
    public Object sendOrder() {
        User user = new User(1,"xwj","2021-7-12 20:55:34");
        rabbitTemplate.convertAndSend("order.ensure",user);
        return "发送成功...";
    }

    @RequestMapping("/sendFount")
    public void sendFount() {
        String message = "Publish/Subscribe-fanout";
        rabbitTemplate.convertAndSend("logs","",message);
    }

    @RequestMapping("/sendRoutingDirect")
    public void testRouteDirect(){
        String message = "Publish/Subscribe-route";
        rabbitTemplate.convertAndSend("directs","info",message);
    }

    @RequestMapping("/sendRoutingTopic")
    public void testRouteTopic(){
        String message = "Publish/Subscribe-topic";
        rabbitTemplate.convertAndSend("topics","user.save",message);
    }


}
