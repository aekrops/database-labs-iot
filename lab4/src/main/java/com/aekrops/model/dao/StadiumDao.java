package com.aekrops.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Stadium;

public class StadiumDao implements AbstractGenericDao<Stadium> {

    public static final String TABLE = "trostynskyi_db.stadium";
    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (name, city, country VALUES (?, ?, ?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET name = ?, city = ?, country = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";

    @Override
    public List<Stadium> findAll() {
        List<Stadium> stadiums = new ArrayList<>();

        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Stadium teamStatistic = new Stadium(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("country")
                );
                stadiums.add(teamStatistic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stadiums;
    }


    @Override
    public Stadium find(Integer id) {
        Stadium stadium = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                stadium = new Stadium(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("country")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stadium;
    }

    @Override
    public void create(Stadium stadium) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, stadium.getName());
            statement.setString(2, stadium.getCity());
            statement.setString(3, stadium.getCountry());
            statement.executeUpdate();

            System.out.println(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, Stadium stadium) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, stadium.getName());
            statement.setString(2, stadium.getCity());
            statement.setString(3, stadium.getCountry());
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
