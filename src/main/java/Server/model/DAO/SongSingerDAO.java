package Server.model.DAO;

import Server.model.DB.SongEntity;
import Server.model.DB.SongSingerEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongSingerDAO {
    public List<SongSingerEntity> getAll() {
        Session s = HibernateUtil.getSession(SongSingerEntity.class);
        List<SongSingerEntity> ls = DBUtil.loadAllData(SongSingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SongSingerEntity save(SongSingerEntity entity){
        Session s =HibernateUtil.getSession(SongSingerEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= HibernateUtil.getSession(SongSingerEntity.class);
        DBUtil.deleteData(id,SongSingerEntity.class,s);
    }
    public SongSingerEntity getByID(long id){
        Session s = HibernateUtil.getSession(SongSingerEntity.class);
        SongSingerEntity entity = DBUtil.getDataByID(id,SongSingerEntity.class,s);
        return entity;
    }
    public List<SongSingerEntity> getId(String conditionColumn, String condition ){
            Session s = HibernateUtil.getSession(SongSingerEntity.class);
            List<SongSingerEntity> entity = DBUtil.getListHasCondition(conditionColumn,condition,SongSingerEntity.class,s);
            return entity;
    }
    public List<SongSingerEntity> getId(Long condition, Long condition1 ){
        Session s = HibernateUtil.getSession(SongSingerEntity.class);
        List<SongSingerEntity> entity = DBUtil.getIdTableM2M("song_singer","songId",condition+"","singerid",condition1+"",SongSingerEntity.class,s);
        return entity;
    }
}
