package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDao implements AbstractGenericDao<Game> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Game> findAll() {
    List<Game> games = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      games = session.createQuery("from Game").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return games;
  }


  @Override
  public Game find(Integer id) {
    Game game = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      game = session.get(Game.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return game;
  }

  @Override
  public void create(Game game) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(game);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Game game) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(game);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Game game = session.get(Game.class, id);
      if (game != null) {
        session.delete(game);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}