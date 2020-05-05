package Server.model.DAO;

import Server.model.DB.DirectorEntity;
import Server.model.DB.FilmActorEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class FilmActorDAO {
   public List<FilmActorEntity> getAll() {
        Session s = HibernateUtil.getSession(FilmActorEntity.class);
        List<FilmActorEntity> ls = DBUtil.loadAllData(FilmActorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public FilmActorEntity save(FilmActorEntity entity){
        Session s = HibernateUtil.getSession(FilmActorEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(FilmActorEntity.class);
        DBUtil.deleteData(id,FilmActorEntity.class,s);
    }
    public FilmActorEntity getByID(Long id){
        Session s = HibernateUtil.getSession(FilmActorEntity.class);
        FilmActorEntity entity = DBUtil.getDataByID(id,FilmActorEntity.class,s);
        return entity;
    }
    public List<FilmActorEntity> getListHasCondition(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(FilmActorEntity.class);
        List<FilmActorEntity> entity = DBUtil.getListHasCondition("film_actor",conditionColumn,condition,FilmActorEntity.class,s);
        return entity;
    }
}
