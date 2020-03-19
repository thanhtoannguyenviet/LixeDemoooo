package Server.model.DAO;

import Server.model.DB.AlbumEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AlbumDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumEntity.class).buildSessionFactory();
    public List<AlbumEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AlbumEntity> ls = DBUtil.loadAllData(AlbumEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(AlbumEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AlbumEntity.class,s);
    }
    public AlbumEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        AlbumEntity entity = DBUtil.GetDataByID(id,AlbumEntity.class,s);
        return entity;
    }
}
