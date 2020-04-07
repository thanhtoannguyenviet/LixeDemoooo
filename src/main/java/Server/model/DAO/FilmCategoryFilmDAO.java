package Server.model.DAO;

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
    public void Save(FilmCategoryfilmEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,FilmCategoryfilmEntity.class,s);
    }
    public FilmCategoryfilmEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        FilmCategoryfilmEntity entity = DBUtil.GetDataByID(id,FilmCategoryfilmEntity.class,s);
        return entity;
    }
}
