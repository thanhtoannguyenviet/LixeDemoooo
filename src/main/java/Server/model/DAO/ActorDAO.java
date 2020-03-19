package Server.model.DAO;

import Server.model.DB.ActorEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class ActorDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ActorEntity.class).buildSessionFactory();
    public List<ActorEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<ActorEntity> ls = DBUtil.loadAllData(ActorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(ActorEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,ActorEntity.class,s);
    }
    public ActorEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        ActorEntity entity = DBUtil.GetDataByID(id,ActorEntity.class,s);
        return entity;
    }
}
