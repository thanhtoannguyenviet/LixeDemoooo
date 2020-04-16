package Server.model.DAO;

import Server.model.DB.SearchEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SearchDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SearchEntity.class).buildSessionFactory();
    public List<SearchEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SearchEntity> ls = DBUtil.loadAllData(SearchEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SearchEntity Save(SearchEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(int id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SearchEntity.class,s);
    }
    public SearchEntity GetByID(int id){
        Session s = factory.getCurrentSession();
        SearchEntity entity = DBUtil.GetDataByID(id,SearchEntity.class,s);
        return entity;
    }
}
