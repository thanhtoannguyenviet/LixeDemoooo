package Server.model.DAO;

import Server.model.DB.AlbumEntity;
import Server.model.DB.AlbumSingerEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collections;
import java.util.List;

public class AlbumSingerDAO {
     public List<AlbumSingerEntity> getAll() {
        Session s = HibernateUtil.getSession(AlbumSingerEntity.class);
        List<AlbumSingerEntity> ls = DBUtil.loadAllData(AlbumSingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AlbumSingerEntity save(AlbumSingerEntity entity){
        Session s = HibernateUtil.getSession(AlbumSingerEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AlbumSingerEntity.class);
        DBUtil.deleteData(id,AlbumSingerEntity.class,s);
    }
    public AlbumSingerEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AlbumSingerEntity.class);
        AlbumSingerEntity entity = DBUtil.getDataByID(id,AlbumSingerEntity.class,s);
        return entity;
    }
    public List<AlbumSingerEntity> getId(String conditionColumn,String condition ){
        Session s = HibernateUtil.getSession(AlbumSingerEntity.class);
        List<AlbumSingerEntity> entity = DBUtil.getListHasCondition("album_singer",conditionColumn,condition,AlbumSingerEntity.class,s);
        return entity;
    }
}
