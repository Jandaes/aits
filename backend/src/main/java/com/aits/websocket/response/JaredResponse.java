package com.aits.websocket.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jared
 * 服务器向浏览器发送此类的消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JaredResponse {
    private String responseMessage;
}
