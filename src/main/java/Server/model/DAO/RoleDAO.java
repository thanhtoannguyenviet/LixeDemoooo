package Server.model.DAO;

import Server.model.DB.LogEntity;
import Server.model.DB.RoleEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class RoleDAO {
   public List<RoleEntity> getAll() {
        Session s = HibernateUtil.getSession(RoleEntity.class);
        List<RoleEntity> ls = DBUtil.loadAllData(RoleEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public RoleEntity save(RoleEntity entity){
        Session s = HibernateUtil.getSession(RoleEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(int id){
        Session s= HibernateUtil.getSession(RoleEntity.class);
        DBUtil.deleteData(id,RoleEntity.class,s);
    }
    public RoleEntity getByID(Long id){
        Session s = HibernateUtil.getSession(RoleEntity.class);
        RoleEntity entity = DBUtil.getDataByID(id,RoleEntity.class,s);
        return entity;
    }
}
