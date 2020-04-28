package Server.model.DAO;

import Server.model.DB.SongCategorysongEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongCategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongCategorysongEntity.class).buildSessionFactory();
    public List<SongCategorysongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SongCategorysongEntity> ls = DBUtil.loadAllData(SongCategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SongCategorysongEntity save(SongCategorysongEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SongCategorysongEntity.class,s);
    }
    public SongCategorysongEntity getByID(long id){
        Session s = factory.getCurrentSession();
        SongCategorysongEntity entity = DBUtil.getDataByID(id,SongCategorysongEntity.class,s);
        return entity;
    }
    public List<SongCategorysongEntity> getId(String conditionColumn, String condition ){
            Session s = factory.getCurrentSession();
            List<SongCategorysongEntity> entity = DBUtil.getListHasCondition("song_categorysong",conditionColumn,condition,SongCategorysongEntity.class,s);
            return Collections.unmodifiableList(entity);
    }
}
