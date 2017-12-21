package com.aits.jms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JmsTest extends BaseTest{
    @Autowired
    private JMSProducer jmsProducer;
    @Autowired
    private Topic topic;
    @Autowired
    private Queue queue;
    private ExecutorService threadPool= Executors.newFixedThreadPool(3);
    @Test
    public void testJms() {
        for (int i = 0; i < 500; i++) {
            jmsProducer.sendMessage(queue,"hello,my name is jared"+i);
        }
    }
}