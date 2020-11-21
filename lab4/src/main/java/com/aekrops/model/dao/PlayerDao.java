package com.aekrops.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Player;

public class PlayerDao implements AbstractGenericDao<Player>{
    
    public static final String TABLE = "trostynskyi_db.player";
    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    // id = ? �� �� ����� ������ �� �� ����� ��� ��� ������ ������ ������� ���������� ����
    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    // (?) �� �� ����� ������ �� �� ����� �� ������� ����
    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (id, team_id, name, age VALUES (?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET team_id = ?, name = ?, age = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";
    
    @Override
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Player player = new Player(
                        resultSet.getInt("id"),
                        resultSet.getInt("team_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                        );
                // � ������ �� ����
                players.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    
    @Override
    public Player find(Integer id) {
        Player player = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                player = new Player(
                        resultSet.getInt("id"),
                        resultSet.getInt("team_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public void create(Player player) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, player.getId());
            statement.setInt(2, player.getTeam_id());
            statement.setString(3, player.getName());
            statement.setInt(4, player.getAge());
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Integer id, Player player) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, player.getTeam_id());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getAge());
            statement.setInt(4, id);
            statement.executeUpdate();
            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
