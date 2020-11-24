package com.aekrops.model.dao;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Coach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDao implements AbstractGenericDao<Coach> {

    public static final String TABLE = "trostynskyi_db.coach";

    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (name, age) VALUES (?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET name = ?, age = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";

    @Override
    public List<Coach> findAll() {
        List<Coach> coaches = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Coach coach = new Coach(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
                coaches.add(coach);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coaches;
    }


    @Override
    public Coach find(Integer id) {
        Coach coach = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coach = new Coach(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coach;
    }

    @Override
    public void create(Coach coach) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, coach.getName());
            statement.setInt(2, coach.getAge());
            statement.executeUpdate();

            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Coach stadium) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, stadium.getName());
            statement.setInt(2, stadium.getAge());
            statement.setInt(3, id);
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
