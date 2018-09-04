package kami.liyf.websocket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Description WebSocketSession信息发送类
 * @ClassName WebSocketSessionSending
 * @author 李英夫
 * @since 2018/9/2 22:45
 * @version V1.0.0
 * @Copyright (c) All Rights Reserved, 2018.
 */
public class WebSocketSessionSending {

    private Logger logger = LoggerFactory.getLogger(WebSocketSessionSending.class);

    private static int num = 0;

    public void sendMessage(){
        logger.info("发送信息函数实例化完成.....");
        LinkedList<WebSocketSession> users = MyWebSocketHandler.users;
        new Thread(() -> {
            ListIterator<WebSocketSession> it;
                while (true){
                    synchronized (users){
                        it = users.listIterator();
                        while (it.hasNext()){
                            WebSocketSession user = it.next();
                            if(!user.isOpen()){
                                logger.info("删除WebSocketSession{}.....",user.getId());
                                it.remove();
                            }else {
                                try {
                                    user.sendMessage(new TextMessage("" + num++));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    try {
                        if(users.size() == 0){
                            Thread.sleep(20000);
                        }else {
                            Thread.sleep(5000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }).start();

    }
}
