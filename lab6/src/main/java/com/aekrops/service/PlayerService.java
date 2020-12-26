package com.aekrops.service;

import com.aekrops.domain.Player;
import com.aekrops.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements AbstractService<Player, Integer> {

  private final PlayerRepository playerRepository;

  public PlayerService(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  public List<Player> getAll() {
    return playerRepository.findAll();
  }

  public List<Player> getAllByTeamId(Integer id) {
    return playerRepository.getAllByTeamId(id);
  }

  @Override
  public Player getById(Integer id) {
    return playerRepository.getOne(id);
  }

  @Override
  public Player create(Player newPlayer) {
    return playerRepository.save(newPlayer);
  }

  @Override
  public Player update(Integer id, Player player) {
    if (playerRepository.findById(id).isPresent()) {
      return playerRepository.save(player);
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteById(Integer id) {
    if (playerRepository.findById(id).isPresent()) {
      playerRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
