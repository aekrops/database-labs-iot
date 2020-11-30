package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao implements AbstractGenericDao<Player> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Player> findAll() {
    List<Player> players = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      players = session.createQuery("From Player").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return players;
  }


  @Override
  public Player find(Integer id) {
    Player player = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      player = session.get(Player.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return player;
  }

  @Override
  public void create(Player player) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(player);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Player player) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(player);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Player player = session.get(Player.class, id);
      if (player != null) {
        session.delete(player);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
