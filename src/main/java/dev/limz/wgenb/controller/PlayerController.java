package dev.limz.wgenb.controller;

import dev.limz.wgenb.dto.PlayerDto;
import dev.limz.wgenb.model.Player;
import dev.limz.wgenb.request.RegisterPlayerBody;
import dev.limz.wgenb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping("/player")
    public Object registerPlayer(@Validated @RequestBody RegisterPlayerBody body){
        var playerCreated = playerService.registerPlayer(body.id());
        return new ResponseEntity<PlayerDto>(playerCreated, HttpStatus.CREATED);
    }

    @GetMapping("player/{id}")
    public ResponseEntity<PlayerDto> findPlayer(@PathVariable String id){
        var player = playerService.getPlayer(id);
        if(player != null) return new ResponseEntity<>(player, HttpStatus.FOUND);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
