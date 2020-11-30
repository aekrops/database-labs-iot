package com.aekrops.model.dao;

import com.aekrops.HibernateUtil;
import com.aekrops.model.entity.Stadium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao implements AbstractGenericDao<Stadium> {

  private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  @Override
  public List<Stadium> findAll() {
    List<Stadium> stadiums = new ArrayList<>();

    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      stadiums = session.createQuery("from Stadium ").getResultList();
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stadiums;
  }


  @Override
  public Stadium find(Integer id) {
    Stadium stadium = null;
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      stadium = session.get(Stadium.class, id);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return stadium;
  }

  @Override
  public void create(Stadium stadium) throws SQLException {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(stadium);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Integer id, Stadium stadium) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      session.save(stadium);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Integer id) {
    try (Session session = sessionFactory.getCurrentSession()) {
      session.beginTransaction();
      Stadium stadium = session.get(Stadium.class, id);
      if (stadium != null) {
        session.delete(stadium);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
