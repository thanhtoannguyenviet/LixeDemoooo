package Server.model.DAO;

import Server.model.DB.SingerEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SingerDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SingerEntity.class).buildSessionFactory();
    public List<SingerEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SingerEntity> ls = DBUtil.loadAllData(SingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SingerEntity Save(SingerEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SingerEntity.class,s);
    }
    public SingerEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        SingerEntity entity = DBUtil.GetDataByID(id,SingerEntity.class,s);
        return entity;
    }
}
