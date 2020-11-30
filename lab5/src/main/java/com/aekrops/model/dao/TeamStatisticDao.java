package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.TeamStatistic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamStatisticDao implements AbstractGenericDao<TeamStatistic> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<TeamStatistic> findAll() {
    List<TeamStatistic> teamStatistics = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      teamStatistics = session.createQuery("from TeamStatistic ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return teamStatistics;
  }


  @Override
  public TeamStatistic find(Integer id) {
    TeamStatistic teamStatistic = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      teamStatistic = session.get(TeamStatistic.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return teamStatistic;
  }

  @Override
  public void create(TeamStatistic teamStatistic) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(teamStatistic);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, TeamStatistic teamStatistic) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(teamStatistic);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      TeamStatistic teamStatistic = session.get(TeamStatistic.class, id);
      if (teamStatistic != null) {
        session.delete(teamStatistic);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
