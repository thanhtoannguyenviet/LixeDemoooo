package Server.model.DAO;

import Server.model.DB.RoleEntity;
import Server.model.DB.SearchEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SearchDAO {
    public List<SearchEntity> getAll() {
        Session s = HibernateUtil.getSession(SearchEntity.class);
        List<SearchEntity> ls = DBUtil.loadAllData(SearchEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SearchEntity Save(SearchEntity entity){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void Delete(int id){
        Session s= HibernateUtil.getSession(SearchEntity.class);
        DBUtil.deleteData(id,SearchEntity.class,s);
    }
    public SearchEntity GetByID(int id){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        SearchEntity entity = DBUtil.getDataByID(id,SearchEntity.class,s);
        return entity;
    }
}
