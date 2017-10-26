package com.aits.websocket.controller;

import com.aits.websocket.message.JaredMessage;
import com.aits.websocket.response.JaredResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author jared
 */
@Controller
public class WsController {
    //当浏览器向服务器发送请求时，通过@messagemapping映射/welcome这个地址,类似@requestmapping
    @MessageMapping("/welcome")
    //当服务器有消息时，会对订阅@sendto中的路径的浏览器发送消息
    @SendTo("/topic/getResponse")
    public JaredResponse say(JaredMessage message) throws Exception {
        Thread.sleep(3000);
        return new JaredResponse("welcome,"+message.getName());
    }
}
