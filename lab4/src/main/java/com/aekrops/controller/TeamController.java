package com.aekrops.controller;

import com.aekrops.model.dao.TeamDao;
import com.aekrops.model.entity.Team;

import java.sql.SQLException;
import java.util.List;

public class TeamController implements AbstractGenericController<Team> {

    private static final TeamDao dao = new TeamDao();

    public TeamController() {

    }

    @Override
    public List<Team> findAll() {
        return dao.findAll();
    }

    @Override
    public Team find(Integer id) {
        return dao.find(id);
    }

    @Override
    public void create(Team team) throws SQLException {
        dao.create(team);
    }

    @Override
    public void update(Integer id, Team team) {
        dao.update(id, team);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

}
