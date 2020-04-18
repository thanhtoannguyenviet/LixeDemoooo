package Server.model.DAO;

import Server.model.DB.AlbumSingerEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collections;
import java.util.List;

public class AlbumSingerDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumSingerEntity.class).buildSessionFactory();
    public List<AlbumSingerEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AlbumSingerEntity> ls = DBUtil.loadAllData(AlbumSingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AlbumSingerEntity save(AlbumSingerEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AlbumSingerEntity.class,s);
    }
    public AlbumSingerEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        AlbumSingerEntity entity = DBUtil.getDataByID(id,AlbumSingerEntity.class,s);
        return entity;
    }
    public List<AlbumSingerEntity> getId(String conditionColumn,String condition ){
        Session s = factory.getCurrentSession();
        List<AlbumSingerEntity> entity = DBUtil.getListHasCondition("album_singer",conditionColumn,condition,AlbumSingerEntity.class,s);
        return entity;
    }
}
