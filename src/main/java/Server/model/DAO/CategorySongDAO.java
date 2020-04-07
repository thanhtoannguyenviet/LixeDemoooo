package Server.model.DAO;

import Server.model.DB.CategorysongEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CategorysongEntity.class).buildSessionFactory();
    public List<CategorysongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CategorysongEntity> ls = DBUtil.loadAllData(CategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(CategorysongEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CategorysongEntity.class,s);
    }
    public CategorysongEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        CategorysongEntity entity = DBUtil.GetDataByID(id,CategorysongEntity.class,s);
        return entity;
    }
}
