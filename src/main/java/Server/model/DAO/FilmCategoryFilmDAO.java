package Server.model.DAO;

import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmCategoryfilmEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmCategoryFilmDAO {
   public List<FilmCategoryfilmEntity> getAll() {
        Session s = HibernateUtil.getSession(FilmCategoryfilmEntity.class);
        List<FilmCategoryfilmEntity> ls = DBUtil.loadAllData(FilmCategoryfilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public FilmCategoryfilmEntity save(FilmCategoryfilmEntity entity){
        Session s = HibernateUtil.getSession(FilmCategoryfilmEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(FilmCategoryfilmEntity.class);
        DBUtil.deleteData(id,FilmCategoryfilmEntity.class,s);
    }
    public FilmCategoryfilmEntity getByID(Long id){
        Session s = HibernateUtil.getSession(FilmCategoryfilmEntity.class);
        FilmCategoryfilmEntity entity = DBUtil.getDataByID(id,FilmCategoryfilmEntity.class,s);
        return entity;
    }
    public List<FilmCategoryfilmEntity> getId(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(FilmCategoryfilmEntity.class);
        List<FilmCategoryfilmEntity> entity = DBUtil.getListHasCondition(conditionColumn,condition,FilmCategoryfilmEntity.class,s);
        return entity;
    }
}
