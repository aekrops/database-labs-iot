package com.aekrops.controller;

import com.aekrops.model.dao.StadiumDao;
import com.aekrops.model.entity.Stadium;

import java.sql.SQLException;
import java.util.List;

public class StadiumController implements AbstractGenericController<Stadium> {

    private static final StadiumDao dao = new StadiumDao();

    public StadiumController() {

    }

    @Override
    public List<Stadium> findAll() {
        return dao.findAll();
    }

    @Override
    public Stadium find(Integer id) {
        return dao.find(id);
    }

    @Override
    public void create(Stadium stadium) throws SQLException {
        dao.create(stadium);
    }

    @Override
    public void update(Integer id, Stadium stadium) {
        dao.update(id, stadium);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

}
