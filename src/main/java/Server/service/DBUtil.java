package Server.service;

import Server.model.DAO.LogDAO;
import Server.model.DB.LogEntity;
import Server.model.DB.UserEntity;
import Server.model.DTO.Criteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

@Repository
public class DBUtil {
    public static <T> List<T> loadAllData(Class<T> type, Session session) {
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        session.getTransaction().commit();
        return data;
    }

    public static <T> List<T> loadDataPagination(Class<T> type, Criteria criter) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        int itemStart = 0;
        int itemEnd = 2;
        if (criter != null) {
            itemStart = criter.getCurrentPage() * criter.getItemPerPage();
            itemEnd = itemStart + criter.getItemPerPage();
        }
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> from = criteriaQuery.from(type);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = session.createQuery(select);
        typedQuery.setFirstResult(itemStart);
        typedQuery.setMaxResults(itemEnd);
        List<T> data = typedQuery.getResultList();
        session.getTransaction().commit();
        return data;
    }

    public static <T> long countData(Class<T> type, Criteria criter){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> from = criteriaQuery.from(type);
        CriteriaQuery<Long> select = criteriaQuery.select(builder.count(from));
        TypedQuery<Long> typedQuery = session.createQuery(select);
        long count = typedQuery.getSingleResult();
        return count;
    }
    public static <T> T addData(T newItem, Session session) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(newItem);
            tx.commit();
        } catch (HibernateException var7) {
            if (tx != null) {
                tx.rollback();
            }
            var7.printStackTrace();
        } finally {
            session.close();
            return newItem;
        }
    }

    public static <T, K> void deleteData(T primaryID, Class<K> cl, Session session) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            K item = session.load(cl, (Serializable)primaryID);
            if (item != null) {
                session.delete(item);
            }

            tx.commit();
        } catch (HibernateException var8) {
            if (tx != null) {
                tx.rollback();
            }

            var8.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static <T, K> K GetDataByID(T primaryID, Class<K> cl, Session session) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            K item = session.get(cl, (Serializable)primaryID);
            tx.commit();
            return item;
        } catch (HibernateException var5) {
            if (tx != null) {
                tx.rollback();
            }

            var5.printStackTrace();
            return null;
        }
    }
    public static <T, K> void deleteData(T primaryID, Class<K> cl ) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(cl).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            K item = (K) session.load(cl, (Serializable) primaryID);
            if (item != null) {
                session.delete(item);
            }
            tx.commit();

        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T, K> K GetDataByID(T primaryID, Class<K> cl) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(cl).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            K item = (K) session.get(cl, (Serializable) primaryID);
            tx.commit();
            return item;
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return null;
    }

    public static <K> K convertToOBject(Object object, Class<K> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object, clazz);

    }

    public static <T> List<T> execCustomSQL(Class<T> clazz, String query) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(clazz).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            SQLQuery q = session.createSQLQuery(query); // bỏ custom SQL vào
            q.setResultTransformer((org.hibernate.Criteria.ALIAS_TO_ENTITY_MAP));

            List<T> data = (List<T>) q.list();
            return data;
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}