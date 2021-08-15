package com.example.rabbitmq.compoent.rabbitmq.service.impl;

import com.example.rabbitmq.compoent.rabbitmq.component.RabbitCompoment;
import com.example.rabbitmq.compoent.rabbitmq.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {
    @Autowired
    private RabbitCompoment rabbitCompoment;

    @Override
    public void sendWork() {
        rabbitCompoment.sendWork();
    }
}
