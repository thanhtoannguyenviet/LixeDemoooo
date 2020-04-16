package Server.model.DAO;

import Server.model.DB.CategoryfilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CategoryfilmEntity.class).buildSessionFactory();
    public List<CategoryfilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CategoryfilmEntity> ls = DBUtil.loadAllData(CategoryfilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public CategoryfilmEntity Save(CategoryfilmEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CategoryfilmEntity.class,s);
    }
    public CategoryfilmEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        CategoryfilmEntity entity = DBUtil.GetDataByID(id,CategoryfilmEntity.class,s);
        return entity;
    }
}
