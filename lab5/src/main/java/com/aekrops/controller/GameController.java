package com.aekrops.controller;

import com.aekrops.model.dao.GameDao;
import com.aekrops.model.entity.Game;

import java.sql.SQLException;
import java.util.List;

public class GameController implements AbstractGenericController<Game> {

  private static final GameDao dao = new GameDao();

  public GameController() {

  }

  @Override
  public List<Game> findAll() {
    return dao.findAll();
  }

  @Override
  public Game find(Integer id) {
    return dao.find(id);
  }

  @Override
  public void create(Game game) throws SQLException {
    dao.create(game);
  }

  @Override
  public void update(Integer id, Game game) {
    dao.update(id, game);
  }

  @Override
  public void delete(Integer id) {
    dao.delete(id);
  }

}
