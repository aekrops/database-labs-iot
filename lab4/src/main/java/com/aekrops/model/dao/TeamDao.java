package com.aekrops.model.dao;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao implements AbstractGenericDao<Team>{
    
    public static final String TABLE = "trostynskyi_db.team";

    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (name, team_statistic_id, coach_id) VALUES (?, ?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET name = ?, team_statistic_id = ?, coach_id = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";
    
    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Team team = new Team(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("team_statistic_id"),
                        resultSet.getInt("coach_id")
                        );
                // � ������ �� ����
                teams.add(team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }


    @Override
    public Team find(Integer id) {
        Team team = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                team = new Team(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("team_statistic_id"),
                        resultSet.getInt("coach_id")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public void create(Team team) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, team.getName());
            statement.setInt(2, team.getTeamStatisticId());
            statement.setInt(3, team.getCoachId());
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Integer id, Team team) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, team.getName());
            statement.setInt(2, team.getTeamStatisticId());
            statement.setInt(3, team.getCoachId());
            statement.setInt(4, team.getId());
            statement.setInt(5, id);
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
