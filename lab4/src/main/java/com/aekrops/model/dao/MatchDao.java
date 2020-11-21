package com.aekrops.model.dao;

import com.aekrops.DatabaseConnector;
import com.aekrops.model.entity.Match;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDao implements AbstractGenericDao<Match> {

    public static final String TABLE = "trostynskyi_db.match";
    private static final String GET_ALL_QUERY = "SELECT * FROM trostynskyi_db.match;";

    // id = ? �� �� ����� ������ �� �� ����� ��� ��� ������ ������ ������� ���������� ����
    private static final String GET_ONE_QUERY = "SELECT * FROM " + TABLE + " WHERE id = ?;";

    // (?) �� �� ����� ������ �� �� ����� �� ������� ����
    private static final String CREATE_QUERY = "INSERT INTO " + TABLE + " (id, season, guests_team, "
                                        + "hosts_team, tournament, referee, stadium, match_date) VALUES (?);";

    private static final String UPDATE_QUERY = "UPDATE " + TABLE + " SET season = ?, guests_team = ?, hosts_team = ?, tournament = ?,"
                            +"referee = ?, stadium = ?, match_date = ? WHERE id = ?;";

    private static final String DELETE_QUERY = "DELETE FROM " + TABLE + " WHERE id = ?;";
    
    @Override
    public List<Match> findAll() {
        List<Match> matches = new ArrayList<>();
        
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ALL_QUERY)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Match match = new Match(
                        resultSet.getInt("id"), // id ��� ��� int - ����� getInt
                        resultSet.getString("season"), // season ��� String - ����� getString
                        resultSet.getString("guests_team"),
                        resultSet.getString("hosts_team"),
                        resultSet.getString("tournament"),
                        resultSet.getString("referee"),
                        resultSet.getString("stadium"),
                        resultSet.getString("match_date")
                        );
                // � ������ �� ����
                matches.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }


    @Override
    public Match find(Integer id) {
        Match match = null;
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET_ONE_QUERY)) {
            // � ����� ���� ���� WHERE id = ?. ��� ����� ������� ��� �������� id - �� �� �����
            // ���� �� ��� ��� ������� �� ����� :)
            statement.setInt(1, id);
            System.out.println(statement);
            // ��� ���� ��������� ������� �� ResultSet, ���� �� ��� ������ ����� ������ �����
            ResultSet resultSet = statement.executeQuery();

            // ���� ���� ����� - ������� ���
            while (resultSet.next()) {
                match = new Match(
                        resultSet.getInt("id"), // id ��� ��� int - ����� getInt
                        resultSet.getString("season"), // season ��� String - ����� getString
                        resultSet.getString("guests_team"),
                        resultSet.getString("hosts_team"),
                        resultSet.getString("tournament"),
                        resultSet.getString("referee"),
                        resultSet.getString("stadium"),
                        resultSet.getString("match_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }

    @Override
    public void create(Match match) throws SQLException {
        // ���� �� ����, ��������� ������� � �������� �����
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, match.getId());
            statement.setString(2, match.getSeason());
            statement.setString(3, match.getGuestsTeam());
            statement.setString(4, match.getHostsTeam());
            statement.setString(5, match.getTournament());
            statement.setString(6, match.getReferee());
            statement.setString(7, match.getStadium());
            statement.setString(8, match.getMatchDate());
            // ��������
            statement.executeUpdate();
            System.out.println(statement);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(Integer id, Match match) {
        try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(UPDATE_QUERY)) {
            // ���� ������������ �����
            // ������ "UPDATE " + TABLE + " SET season = ?, guests_team = ?, hosts_team = ?, tournament = ?,"
            // +"referee = ?, stadium = ?, match_date = ? WHERE id = ?;"
            statement.setString(1, match.getSeason());
            statement.setString(2, match.getGuestsTeam());
            statement.setString(3, match.getHostsTeam());
            statement.setString(4, match.getTournament());
            statement.setString(5, match.getReferee());
            statement.setString(6, match.getStadium());
            statement.setString(7, match.getMatchDate());
            // ������ id = ?
            statement.setInt(8, id);
            // �������� ��������
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