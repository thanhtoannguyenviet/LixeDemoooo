package Server.model.DAO;

import Server.model.DB.SongSingerEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongSingerDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongSingerEntity.class).buildSessionFactory();
    public List<SongSingerEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SongSingerEntity> ls = DBUtil.loadAllData(SongSingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(SongSingerEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SongSingerEntity.class,s);
    }
    public SongSingerEntity GetByID(long id){
        Session s = factory.getCurrentSession();
        SongSingerEntity entity = DBUtil.GetDataByID(id,SongSingerEntity.class,s);
        return entity;
    }
}
