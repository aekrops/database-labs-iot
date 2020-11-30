package com.aekrops.controller;

import com.aekrops.model.dao.CoachDao;
import com.aekrops.model.entity.Coach;

import java.sql.SQLException;
import java.util.List;

public class CoachController implements AbstractGenericController<Coach> {

    private static final CoachDao dao = new CoachDao();

    public CoachController() {

    }

    @Override
    public List<Coach> findAll() {
        return dao.findAll();
    }

    @Override
    public Coach find(Integer id) {
        return dao.find(id);
    }

    @Override
    public void create(Coach coach) throws SQLException {
        dao.create(coach);
    }

    @Override
    public void update(Integer id, Coach coach) {
        dao.update(id, coach);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

}

