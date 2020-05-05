package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.CategorysongEntity;
import Server.model.DB.CommentEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CommentDAO {
    public List<CommentEntity> getAll() {
        Session s = HibernateUtil.getSession(CommentEntity.class);
        List<CommentEntity> ls = DBUtil.loadAllData(CommentEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public CommentEntity save(CommentEntity entity){
        Session s = HibernateUtil.getSession(CommentEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(CommentEntity.class);
        DBUtil.deleteData(id,CommentEntity.class,s);
    }
    public CommentEntity getByID(Long id){
        Session s = HibernateUtil.getSession(CommentEntity.class);
        CommentEntity entity = DBUtil.getDataByID(id,CommentEntity.class,s);
        return entity;
    }
    public List<CommentEntity> getId(String model,long entryId ){

        Session s = HibernateUtil.getSession(CommentEntity.class);
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
