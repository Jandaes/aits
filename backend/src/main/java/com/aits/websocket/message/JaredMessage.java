package com.aits.websocket.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jared
 * 浏览器向服务端发送的消息用此类接收
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JaredMessage {
    private String name;
}
