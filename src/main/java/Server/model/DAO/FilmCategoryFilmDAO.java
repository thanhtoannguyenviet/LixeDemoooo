package Server.model.DAO;

import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmCategoryfilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmCategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FilmCategoryfilmEntity.class).buildSessionFactory();
    public List<FilmCategoryfilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<FilmCategoryfilmEntity> ls = DBUtil.loadAllData(FilmCategoryfilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public FilmCategoryfilmEntity save(FilmCategoryfilmEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,FilmCategoryfilmEntity.class,s);
    }
    public FilmCategoryfilmEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        FilmCategoryfilmEntity entity = DBUtil.getDataByID(id,FilmCategoryfilmEntity.class,s);
        return entity;
    }
    public List<FilmCategoryfilmEntity> getId(String conditionColumn, String condition ){
        Session s = factory.getCurrentSession();
        List<FilmCategoryfilmEntity> entity = DBUtil.getListHasCondition("film_categoryfilm",conditionColumn,condition,FilmCategoryfilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
