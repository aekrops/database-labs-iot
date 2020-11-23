package com.aekrops.model.dao;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.TeamStatistic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamStatisticDao implements AbstractGenericDao<TeamStatistic> {
    
    public static final String TABLE = "trostynskyi_db.team_statistic";
    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (victories, percentage_hits_on_target VALUES (?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET victories = ?, percentage_hits_on_target = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";
    
    @Override
    public List<TeamStatistic> findAll() {
        List<TeamStatistic> teamStatistics = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TeamStatistic teamStatistic = new TeamStatistic(
                        resultSet.getInt("id"),
                        resultSet.getInt("victories"),
                        resultSet.getInt("percentage_hits_on_target")
                        );
                teamStatistics.add(teamStatistic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamStatistics;
    }

    
    @Override
    public TeamStatistic find(Integer id) {
        TeamStatistic teamStatistic = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teamStatistic = new TeamStatistic(
                        resultSet.getInt("id"),
                        resultSet.getInt("victories"),
                        resultSet.getInt("percentage_hits_on_target")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teamStatistic;
    }

    @Override
    public void create(TeamStatistic teamStatistic) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, teamStatistic.getVictories());
            statement.setInt(2, teamStatistic.getPercentageHitsOnTarget());
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Integer id, TeamStatistic teamStatistic) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, teamStatistic.getVictories());
            statement.setInt(2, teamStatistic.getPercentageHitsOnTarget());
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
