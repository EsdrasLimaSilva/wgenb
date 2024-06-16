package dev.limz.wgenb.controller;

import dev.limz.wgenb.model.GameObjectMapper;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class Stomp {
    @MessageMapping("/")
    @SendTo("/responses")
    public Object greeting(GameObjectMapper gameObjectMapper) throws Exception{
        Thread.sleep(1000);
        return new GameObjectMapper("This is the response");
    }
}
