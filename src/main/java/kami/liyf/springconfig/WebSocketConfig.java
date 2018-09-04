package kami.liyf.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kami.liyf.websocket.HandshakeInterceptor;
import kami.liyf.websocket.MyWebSocketHandler;
import kami.liyf.websocket.WebSocketSessionSending;

//@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler(),"/websocket/demo").
                addInterceptors(new HandshakeInterceptor()).setAllowedOrigins("*");
                //.withSockJS();
    }

    /**
     * webSocket处理bean
     * @return MyWebSocketHandler
     */
    @Bean
    public MyWebSocketHandler myWebSocketHandler(){
        return new MyWebSocketHandler();
    }

    @Bean(initMethod = "sendMessage")
    public WebSocketSessionSending webSocketSessionSending(){
        return new WebSocketSessionSending();
    }
}
