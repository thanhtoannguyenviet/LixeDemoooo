package Server.model.DAO;

import Server.model.DB.FilmCategoryFilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class FilmCategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FilmCategoryFilmEntity.class).buildSessionFactory();
    public List<FilmCategoryFilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<FilmCategoryFilmEntity> ls = DBUtil.loadAllData(FilmCategoryFilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(FilmCategoryFilmEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,FilmCategoryFilmEntity.class,s);
    }
    public FilmCategoryFilmEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        FilmCategoryFilmEntity entity = DBUtil.GetDataByID(id,FilmCategoryFilmEntity.class,s);
        return entity;
    }
}
