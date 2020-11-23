package com.aekrops.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Referee;

public class RefereeDao implements AbstractGenericDao<Referee> {

    public static final String TABLE = "trostynskyi_db.referee";
    private static final String GET_ALL_QUERY = "SELECT * FROM " + TABLE + ";";

    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (name, age VALUES (?, ?);";
    
    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET name = ?, age = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";
    
    @Override
    public List<Referee> findAll() {
        List<Referee> referies = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Referee referee = new Referee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                        );
                referies.add(referee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return referies;
    }

    
    @Override
    public Referee find(Integer id) {
        Referee referee = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                referee = new Referee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return referee;
    }

    @Override
    public void create(Referee referee) throws SQLException {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setString(1, referee.getName());
            statement.setInt(2, referee.getAge());
            statement.executeUpdate();
            
            System.out.println(statement);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Integer id, Referee referee) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, referee.getName());
            statement.setInt(2, referee.getAge());
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
