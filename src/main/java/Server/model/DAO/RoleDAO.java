package Server.model.DAO;

import Server.model.DB.RoleEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class RoleDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(RoleEntity.class).buildSessionFactory();
    public List<RoleEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<RoleEntity> ls = DBUtil.loadAllData(RoleEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public RoleEntity Save(RoleEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(int id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,RoleEntity.class,s);
    }
    public RoleEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        RoleEntity entity = DBUtil.GetDataByID(id,RoleEntity.class,s);
        return entity;
    }
}
