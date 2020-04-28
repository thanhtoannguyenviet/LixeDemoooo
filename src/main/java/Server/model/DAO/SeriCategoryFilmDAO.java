package Server.model.DAO;

import Server.model.DB.SeriCategoryfilmEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SeriCategoryFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SeriCategoryfilmEntity.class).buildSessionFactory();
    public List<SeriCategoryfilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SeriCategoryfilmEntity> ls = DBUtil.loadAllData(SeriCategoryfilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SeriCategoryfilmEntity save(SeriCategoryfilmEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SeriCategoryfilmEntity.class,s);
    }
    public SeriCategoryfilmEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        SeriCategoryfilmEntity entity = DBUtil.getDataByID(id,SeriCategoryfilmEntity.class,s);
        return entity;
    }
    public List<SeriCategoryfilmEntity> getId(String conditionColumn, String condition ){
        Session s = factory.getCurrentSession();
        List<SeriCategoryfilmEntity> entity = DBUtil.getListHasCondition("seri_categoryfilm",conditionColumn,condition,SeriCategoryfilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
