package com.aits.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class JMSConsumer {
    private final static Logger logger = LoggerFactory.getLogger(JMSConsumer.class);
    private ExecutorService threadPool= Executors.newFixedThreadPool(6);
    @JmsListener(destination = JmsConfig.TOPIC,containerFactory = "jmsListenerContainerTopic")
    public void onTopicMessage(String msg) {
        logger.info("接收到topic消息：{}",msg);
    }

    @JmsListener(destination = JmsConfig.QUEUE,containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(String msg) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("接收线程："+Thread.currentThread().getName()+"接收消息："+msg);
            }
        });
       logger.info("接收到queue消息：{}",msg);
    }
}