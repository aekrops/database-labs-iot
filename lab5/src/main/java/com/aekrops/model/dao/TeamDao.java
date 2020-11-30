package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao implements AbstractGenericDao<Team> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Team> findAll() {
    List<Team> teams = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      teams = session.createQuery("from Team ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return teams;
  }


  @Override
  public Team find(Integer id) {
    Team team = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      team = session.get(Team.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return team;
  }

  @Override
  public void create(Team team) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(team);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Team team) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(team);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Team team = session.get(Team.class, id);
      if (team != null) {
        session.delete(team);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
