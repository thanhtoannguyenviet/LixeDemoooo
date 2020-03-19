package Server.model.DAO;

import Server.model.DB.CategoryFilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CategoryFilmEntity.class).buildSessionFactory();
    public List<CategoryFilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CategoryFilmEntity> ls = DBUtil.loadAllData(CategoryFilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(CategoryFilmEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CategoryFilmEntity.class,s);
    }
    public CategoryFilmEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        CategoryFilmEntity entity = DBUtil.GetDataByID(id,CategoryFilmEntity.class,s);
        return entity;
    }
}
