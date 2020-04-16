package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.CommentEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CommentDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CommentEntity.class).buildSessionFactory();
    public List<CommentEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CommentEntity> ls = DBUtil.loadAllData(CommentEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public CommentEntity Save(CommentEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CommentEntity.class,s);
    }
    public CommentEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        CommentEntity entity = DBUtil.GetDataByID(id,CommentEntity.class,s);
        return entity;
    }
    public List<CommentEntity> GetId(String model,long entryId ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetIdFromImageOrResource("Comment",model,entryId);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(CommentEntity.class);
            return q.getResultList();
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }
}
