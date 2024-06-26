package dev.limz.wgenb.controller;

import dev.limz.wgenb.exceptions.PlayerAlreadyExistsException;
import dev.limz.wgenb.request.ApiResponse;
import dev.limz.wgenb.request.RegisterPlayerBody;
import dev.limz.wgenb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ApiResponse registerPlayer(@Validated @RequestBody RegisterPlayerBody body){
        try{
            var playerCreated = playerService.registerPlayer(body.id());
            return new ApiResponse("Player created successfully", true, playerCreated, HttpStatus.CREATED);
        }catch(PlayerAlreadyExistsException e){
            return new ApiResponse("Invalid id", false, null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/player/{id}")
    public ApiResponse findPlayer(@PathVariable String id){
        var player = this.playerService.getPlayer(id);

        if(player != null)
            return new ApiResponse("Player found successfully", true, player, HttpStatus.OK);

        return new ApiResponse("Player not found", false, null, HttpStatus.NOT_FOUND);
    }
}
