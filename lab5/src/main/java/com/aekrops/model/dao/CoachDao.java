package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Coach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDao implements AbstractGenericDao<Coach> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Coach> findAll() {
    List<Coach> coaches = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      coaches = session.createQuery("from Coach").getResultList();
      session.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return coaches;
  }


  @Override
  public Coach find(Integer id) {
    Coach coach = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      coach = session.get(Coach.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return coach;
  }

  @Override
  public void create(Coach coach) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(coach);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Coach coach) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(coach);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Coach coach = session.get(Coach.class, id);
      if (coach != null) {
        session.delete(coach);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
