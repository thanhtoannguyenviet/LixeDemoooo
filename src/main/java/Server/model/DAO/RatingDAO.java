package Server.model.DAO;

import Server.model.DB.LogEntity;
import Server.model.DB.RatingEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class RatingDAO {
    public List<RatingEntity> getAll() {
        Session s = HibernateUtil.getSession(LogEntity.class);
        List<RatingEntity> ls = DBUtil.loadAllData(RatingEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public RatingEntity save(RatingEntity entity){
        Session s = HibernateUtil.getSession(LogEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= HibernateUtil.getSession(LogEntity.class);
        DBUtil.deleteData(id,RatingEntity.class,s);
    }
    public RatingEntity getByID(long id){
        Session s = HibernateUtil.getSession(LogEntity.class);
        RatingEntity entity = DBUtil.getDataByID(id,RatingEntity.class,s);
        return entity;
    }
}
