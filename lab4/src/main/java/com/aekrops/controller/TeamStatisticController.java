package com.aekrops.controller;

import com.aekrops.model.dao.TeamStatisticDao;
import com.aekrops.model.entity.TeamStatistic;

import java.sql.SQLException;
import java.util.List;

public class TeamStatisticController implements AbstractGenericController<TeamStatistic> {

    private static final TeamStatisticDao dao = new TeamStatisticDao();

    public TeamStatisticController() {

    }

    @Override
    public List<TeamStatistic> findAll() {
        return dao.findAll();
    }

    @Override
    public TeamStatistic find(Integer id) {
        return dao.find(id);
    }

    @Override
    public void create(TeamStatistic teamStatistic) throws SQLException {
        dao.create(teamStatistic);
    }

    @Override
    public void update(Integer id, TeamStatistic teamStatistic) {
        dao.update(id, teamStatistic);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

}