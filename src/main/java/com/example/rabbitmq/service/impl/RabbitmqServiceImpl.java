package com.example.rabbitmq.service.impl;

import com.example.rabbitmq.component.RabbitCompoment;
import com.example.rabbitmq.service.RabbitmqService;
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
