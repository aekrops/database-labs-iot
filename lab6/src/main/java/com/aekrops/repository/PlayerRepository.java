package com.aekrops.repository;

import com.aekrops.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

  public List<Player> getAllByTeamId(Integer id);
}
