package com.aekrops.controller;

import java.sql.SQLException;
import java.util.List;

import com.aekrops.model.dao.PlayerDao;
import com.aekrops.model.entity.Player;

public class PlayerController implements AbstractGenericController<Player> {

 private static final PlayerDao dao = new PlayerDao();
    
    public PlayerController() {
        
    }
    
    @Override
    public List<Player> findAll() {
        return dao.findAll();
    }
    
    @Override
    public Player find(Integer id) {
        return dao.find(id);
    }
    
    @Override
    public void create(Player player) throws SQLException {
        dao.create(player);
    }
    
    @Override
    public void update(Integer id, Player player) {
        dao.update(id, player);
    }
    
    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
    
}

