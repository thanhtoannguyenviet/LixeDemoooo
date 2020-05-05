package Server.model.DAO;

import Server.model.DB.SearchEntity;
import Server.model.DB.SeriCategoryfilmEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SeriCategoryFilmDAO {
   public List<SeriCategoryfilmEntity> getAll() {
        Session s = HibernateUtil.getSession(SeriCategoryfilmEntity.class);
        List<SeriCategoryfilmEntity> ls = DBUtil.loadAllData(SeriCategoryfilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SeriCategoryfilmEntity save(SeriCategoryfilmEntity entity){
        Session s = HibernateUtil.getSession(SeriCategoryfilmEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(SeriCategoryfilmEntity.class);
        DBUtil.deleteData(id,SeriCategoryfilmEntity.class,s);
    }
    public SeriCategoryfilmEntity getByID(Long id){
        Session s = HibernateUtil.getSession(SeriCategoryfilmEntity.class);
        SeriCategoryfilmEntity entity = DBUtil.getDataByID(id,SeriCategoryfilmEntity.class,s);
        return entity;
    }
    public List<SeriCategoryfilmEntity> getId(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(SeriCategoryfilmEntity.class);
        List<SeriCategoryfilmEntity> entity = DBUtil.getListHasCondition("seri_categoryfilm",conditionColumn,condition,SeriCategoryfilmEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
