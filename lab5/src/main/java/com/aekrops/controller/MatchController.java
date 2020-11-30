package com.aekrops.controller;

import com.aekrops.model.dao.MatchDao;
import com.aekrops.model.entity.Game;

import java.sql.SQLException;
import java.util.List;

public class MatchController implements AbstractGenericController<Game> {

  private static final MatchDao dao = new MatchDao();

  public MatchController() {

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
