package Server.service;
import Server.model.DTO.Criteria;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

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
    public static <T> List<T> loadAllData(Class<T> type, Session session, Criteria criter) {
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        int itemStart = 0;
        int itemEnd = 1;
        if(criter != null){
            itemStart = criter.getCurrentPage()*criter.getItemPerPage();
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
    public static <T> T addData(T newItem, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(newItem);
            tx.commit();
            return newItem;
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        }
        finally {
            session.close();

        }
    }
    public static <T,K> void deleteData(T primaryID, Class<K> cl,Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            K item =(K) session.load(cl,(Serializable) primaryID);
            if(item!=null){
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
    public static <T,K> K GetDataByID(T primaryID,Class<K> cl,Session session){
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            K item=(K)session.get(cl,(Serializable) primaryID);
            tx.commit();
            return item;
        }catch (HibernateException ex){
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return null;
    }
    public static <K> K convertToOBject(Object object, Class <K> clazz){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object,clazz);

    }

}