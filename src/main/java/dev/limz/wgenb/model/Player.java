package dev.limz.wgenb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@RedisHash("player")
public class Player implements Serializable {

    @Id
    private String id;

    public Player(String id) {
        this.id = id;
    }


}
