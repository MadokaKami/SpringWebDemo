package kami.liyf.websocket;

import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Description webSocket处理类
 * @ClassName MyWebSocketHandler
 * @author 李英夫
 * @since 2018/8/29 22:45
 * @version V1.0.0
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class MyWebSocketHandler implements WebSocketHandler {

    // 保存所有的用户session
    static final LinkedList<WebSocketSession> users = new LinkedList<>();

    private Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 处理消息 msgContent消息内容
        TextMessage textMessage = new TextMessage(message.getPayload().toString(), true);
        // 调用方法（发送消息给所有人）
        sendMsgToAllUsers(textMessage);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("id为{}的session连接错误，错误信息:{}",session.getId(), exception.getMessage());
        session.close();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("id为{}的session连接关闭，关闭状态:{}-{}",session.getId(), closeStatus.getCode(), closeStatus.getReason());
        session.close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    // 给所有用户发送 信息
    private void sendMsgToAllUsers(WebSocketMessage<?> message) throws Exception{

        for (WebSocketSession user : users) {
            user.sendMessage(message);
        }

    }
}
