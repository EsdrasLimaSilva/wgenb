package dev.limz.wgenb.repository;

import dev.limz.wgenb.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {
}
