package kami.liyf.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @Description webSocket拦截器
 * @ClassName HandshakeInterceptor
 * @author 李英夫
 * @since 2018/8/29 22:37
 * @version V1.0.0
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        System.out.println("++++++++++++++++ HandshakeInterceptor: beforeHandshake  ++++++++++++++"+attributes);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }



    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {


        System.out.println("++++++++++++++++ HandshakeInterceptor: afterHandshake  ++++++++++++++");


        super.afterHandshake(request, response, wsHandler, ex);
    }
}
