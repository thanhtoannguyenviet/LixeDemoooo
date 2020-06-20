package Server.model.DAO;

import Server.model.DB.*;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class DirectorFilmDAO {
    public DirectorFilmEntity save(DirectorFilmEntity entity){
        Session s = HibernateUtil.getSession(DirectorFilmEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(DirectorFilmEntity.class);
        DBUtil.deleteData(id,DirectorFilmEntity.class,s);
    }
    public List<DirectorFilmEntity> getID(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(DirectorFilmEntity.class);
        List<DirectorFilmEntity> entity = DBUtil.getListHasCondition("director_film",conditionColumn,condition,DirectorFilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
    public DirectorFilmEntity getByID(Long id){
        Session s = HibernateUtil.getSession(DirectorFilmEntity.class);
        DirectorFilmEntity entity = DBUtil.getDataByID(id,DirectorFilmEntity.class,s);
        return entity;
    }
}
