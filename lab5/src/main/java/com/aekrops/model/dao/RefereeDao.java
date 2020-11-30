package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Referee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefereeDao implements AbstractGenericDao<Referee> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Referee> findAll() {
    List<Referee> referies = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      referies = session.createQuery("from Referee ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return referies;
  }


  @Override
  public Referee find(Integer id) {
    Referee referee = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      referee = session.get(Referee.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return referee;
  }

  @Override
  public void create(Referee referee) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(referee);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Referee referee) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(referee);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Referee referee = session.get(Referee.class, id);
      if (referee != null) {
        session.delete(referee);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
