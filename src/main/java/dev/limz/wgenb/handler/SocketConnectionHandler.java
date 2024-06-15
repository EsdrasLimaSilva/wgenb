package dev.limz.wgenb.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.limz.wgenb.model.GameObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SocketConnectionHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        super.afterConnectionEstablished(session);
        System.out.println(session.getId() + " connected");
        this.webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println(session.getId() + " Disconnected");
        this.webSocketSessions.remove(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{
        super.handleMessage(session, message);

        if(message instanceof TextMessage){
            String payload = ((TextMessage)message).getPayload();
            var gameObject = objectMapper.readValue(payload, GameObjectMapper.class);

            var responseObject = new GameObjectMapper("This is the response!");
            String jsonResponse = objectMapper.writeValueAsString(responseObject);

            for(var webSocketSession : this.webSocketSessions) {
                if (session == webSocketSession) {
                    webSocketSession.sendMessage(new TextMessage(jsonResponse));
                    continue;
                };

                webSocketSession.sendMessage(message);
            }
        }


    }
}
