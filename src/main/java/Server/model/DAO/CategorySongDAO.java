package Server.model.DAO;

import Server.model.DB.CategorySongEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CategorySongEntity.class).buildSessionFactory();
    public List<CategorySongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CategorySongEntity> ls = DBUtil.loadAllData(CategorySongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(CategorySongEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CategorySongEntity.class,s);
    }
    public CategorySongEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        CategorySongEntity entity = DBUtil.GetDataByID(id,CategorySongEntity.class,s);
        return entity;
    }
}
