package Server.model.DAO;

import Server.model.DB.SeriCategoryfilmEntity;
import Server.model.DB.SerifilmFilmEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class SerifilmFilmDAO {
    public List<SerifilmFilmEntity> getAll() {
        Session s = HibernateUtil.getSession(SerifilmFilmEntity.class);
        List<SerifilmFilmEntity> ls = DBUtil.loadAllData(SerifilmFilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SerifilmFilmEntity save(SerifilmFilmEntity entity){
        Session s = HibernateUtil.getSession(SerifilmFilmEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(SerifilmFilmEntity.class);
        DBUtil.deleteData(id,SerifilmFilmEntity.class,s);
    }
    public SerifilmFilmEntity getByID(Long id){
        Session s = HibernateUtil.getSession(SerifilmFilmEntity.class);
        SerifilmFilmEntity entity = DBUtil.getDataByID(id,SerifilmFilmEntity.class,s);
        return entity;
    }
    public List<SerifilmFilmEntity> getId(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(SerifilmFilmEntity.class);
        List<SerifilmFilmEntity> entity = DBUtil.getListHasCondition("serifilm_film",conditionColumn,condition,SerifilmFilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
    public List<SerifilmFilmEntity> getId(Long condition, Long condition1 ){
        Session s = HibernateUtil.getSession(SerifilmFilmEntity.class);
        List<SerifilmFilmEntity> entity = DBUtil.getIdTableM2M("serifilm_film","seriid",condition+"","filmid",condition1+"",SerifilmFilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
