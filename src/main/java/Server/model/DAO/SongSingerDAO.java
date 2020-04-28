package Server.model.DAO;

import Server.model.DB.SongSingerEntity;
import Server.service.DBUtil;
import org.hibernate.*;
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
    public SongSingerEntity save(SongSingerEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SongSingerEntity.class,s);
    }
    public SongSingerEntity getByID(long id){
        Session s = factory.getCurrentSession();
        SongSingerEntity entity = DBUtil.getDataByID(id,SongSingerEntity.class,s);
        return entity;
    }
    public List<SongSingerEntity> getId(String conditionColumn, String condition ){
            Session s = factory.getCurrentSession();
            List<SongSingerEntity> entity = DBUtil.getListHasCondition("song_singer",conditionColumn,condition,SongSingerEntity.class,s);
            return Collections.unmodifiableList(entity);
    }
}
