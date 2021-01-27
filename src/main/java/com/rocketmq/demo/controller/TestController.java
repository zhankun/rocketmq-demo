package com.rocketmq.demo.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhankun
 */
@RequestMapping(value = "test")
@RestController
public class TestController {

    @Value("${rocketmq.producer.topics}")
    private String topic;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping(value = "send/{message}")
    public String test(@PathVariable String message) throws Exception {
        Message msg = new Message(topic, "test_tag", message.getBytes());
        SendResult result = defaultMQProducer.send(msg);
        System.out.println(result);
        return "success";
    }
}
