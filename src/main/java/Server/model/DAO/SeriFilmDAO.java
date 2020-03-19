package Server.model.DAO;

import Server.model.DB.SeriFilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SeriFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SeriFilmEntity.class).buildSessionFactory();
    public List<SeriFilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SeriFilmEntity> ls = DBUtil.loadAllData(SeriFilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(SeriFilmEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SeriFilmEntity.class,s);
    }
    public SeriFilmEntity GetByID(long id){
        Session s = factory.getCurrentSession();
        SeriFilmEntity entity = DBUtil.GetDataByID(id,SeriFilmEntity.class,s);
        return entity;
    }
}
