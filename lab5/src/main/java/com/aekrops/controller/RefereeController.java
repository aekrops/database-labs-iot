package com.aekrops.controller;

import com.aekrops.model.dao.RefereeDao;
import com.aekrops.model.entity.Referee;

import java.sql.SQLException;
import java.util.List;

public class RefereeController implements AbstractGenericController<Referee> {

  private static final RefereeDao dao = new RefereeDao();

  public RefereeController() {

  }

  @Override
  public List<Referee> findAll() {
    return dao.findAll();
  }

  @Override
  public Referee find(Integer id) {
    return dao.find(id);
  }

  @Override
  public void create(Referee referee) throws SQLException {
    dao.create(referee);
  }

  @Override
  public void update(Integer id, Referee referee) {
    dao.update(id, referee);
  }

  @Override
  public void delete(Integer id) {
    dao.delete(id);
  }

}
