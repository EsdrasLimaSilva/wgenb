package dev.limz.wgenb.service;

import dev.limz.wgenb.dto.PlayerDto;
import dev.limz.wgenb.exceptions.PlayerAlreadyExistsException;
import dev.limz.wgenb.model.Player;
import dev.limz.wgenb.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public PlayerDto registerPlayer(String playerId) throws PlayerAlreadyExistsException{
        var player = new Player(playerId);
        var createdPlayer = playerRepository.save(player);

        if(playerRepository.findById(playerId).isPresent()) throw new PlayerAlreadyExistsException();

        return new PlayerDto(createdPlayer.getId());
    }

    public PlayerDto getPlayer(String playerId){
        var playerFound = playerRepository.findById(playerId);
        return playerFound.map(player -> new PlayerDto(player.getId())).orElse(null);

    }
}
