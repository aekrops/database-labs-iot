package com.aekrops.service;

import com.aekrops.domain.Game;
import com.aekrops.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements  AbstractService<Game, Integer> {

  private final GameRepository gameRepository;

  public GameService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  @Override
  public List<Game> getAll() {
    return gameRepository.findAll();
  }

  @Override
  public Game getById(Integer id) {
    return gameRepository.getOne(id);
  }

  @Override
  public Game create(Game newGame) {
    return gameRepository.save(newGame);
  }

  @Override
  public Game update(Integer id, Game game) {
    if (gameRepository.findById(id).isPresent()) {
      return gameRepository.save(game);
    } else {
      return null;
    }
  }

  @Override
  public void deleteById(Integer id) {
    if (gameRepository.findById(id).isPresent()) {
      gameRepository.deleteById(id);
    }
  }
}
