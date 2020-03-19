package Server.model.DAO;

import Server.model.DB.RatingEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class RatingDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(RatingEntity.class).buildSessionFactory();
    public List<RatingEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<RatingEntity> ls = DBUtil.loadAllData(RatingEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(RatingEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,RatingEntity.class,s);
    }
    public RatingEntity GetByID(long id){
        Session s = factory.getCurrentSession();
        RatingEntity entity = DBUtil.GetDataByID(id,RatingEntity.class,s);
        return entity;
    }
}
