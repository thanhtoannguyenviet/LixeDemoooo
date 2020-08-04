package Server.model.DAO;

import Server.model.DB.SingerEntity;
import Server.model.DB.SongCategorysongEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongCategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongCategorysongEntity.class).buildSessionFactory();
    public List<SongCategorysongEntity> getAll() {
        Session s = HibernateUtil.getSession(SongCategorysongEntity.class);
        List<SongCategorysongEntity> ls = DBUtil.loadAllData(SongCategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SongCategorysongEntity save(SongCategorysongEntity entity){
        Session s = HibernateUtil.getSession(SongCategorysongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= HibernateUtil.getSession(SongCategorysongEntity.class);
        DBUtil.deleteData(id,SongCategorysongEntity.class,s);
    }
    public SongCategorysongEntity getByID(long id){
        Session s = HibernateUtil.getSession(SongCategorysongEntity.class);
        SongCategorysongEntity entity = DBUtil.getDataByID(id,SongCategorysongEntity.class,s);
        return entity;
    }
    public List<SongCategorysongEntity> getId(String conditionColumn, String condition ){
            Session s = HibernateUtil.getSession(SongCategorysongEntity.class);
            List<SongCategorysongEntity> entity = DBUtil.getListHasCondition("song_categorysong",conditionColumn,condition,SongCategorysongEntity.class,s);
            return Collections.unmodifiableList(entity);
    }
    public List<SongCategorysongEntity> getId(Long condition, Long condition1 ){
        Session s = HibernateUtil.getSession(SongCategorysongEntity.class);
        List<SongCategorysongEntity> entity = DBUtil.getIdTableM2M("song_categorysong","songid",condition+"","categoryid",condition1+"",SongCategorysongEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
