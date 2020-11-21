package com.aekrops.controller;

import java.sql.SQLException;
import java.util.List;

import com.aekrops.model.dao.MatchDao;
import com.aekrops.model.entity.Match;

public class MatchController implements AbstractGenericController<Match> {

    private static final MatchDao dao = new MatchDao();
    
    public MatchController() {
        
    }
    
    @Override
    public List<Match> findAll() {
        return dao.findAll();
    }
    
    @Override
    public Match find(Integer id) {
        return dao.find(id);
    }
    
    @Override
    public void create(Match match) throws SQLException {
        dao.create(match);
    }
    
    @Override
    public void update(Integer id, Match match) {
        dao.update(id, match);
    }
    
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
    
}
