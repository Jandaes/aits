package com.aits.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author jared
 */
@Configuration
//开启websocket支持，使用stomp协议来传输基于代理（message broker）的消息，控制器也支持@messageMapping等价@requestMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{
    /**
     * 注册stomp协议的节点(endpoint)。并映射指定url
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册stomp的endpoint，并指定使用sockjs协议，设置前端跨域
        registry.addEndpoint("/endPointJared").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置消息代理（message Broker）
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic");
    }
}
