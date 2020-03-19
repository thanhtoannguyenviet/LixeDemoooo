package Server.model.DAO;

import Server.model.DB.SeriCategoryFilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SeriCategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SeriCategoryFilmEntity.class).buildSessionFactory();
    public List<SeriCategoryFilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SeriCategoryFilmEntity> ls = DBUtil.loadAllData(SeriCategoryFilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(SeriCategoryFilmEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SeriCategoryFilmEntity.class,s);
    }
    public SeriCategoryFilmEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        SeriCategoryFilmEntity entity = DBUtil.GetDataByID(id,SeriCategoryFilmEntity.class,s);
        return entity;
    }
}
