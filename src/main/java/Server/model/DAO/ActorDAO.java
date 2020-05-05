package Server.model.DAO;

import Server.model.DB.ActorEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class ActorDAO {
    public List<ActorEntity> getAll() {
        Session s = HibernateUtil.getSession(ActorEntity.class);
        List<ActorEntity> ls = DBUtil.loadAllData(ActorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public ActorEntity save(ActorEntity entity){
        Session s = HibernateUtil.getSession(ActorEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s = HibernateUtil.getSession(ActorEntity.class);
        DBUtil.deleteData(id,ActorEntity.class,s);
    }
    public ActorEntity getByID(Long id){
        Session s = HibernateUtil.getSession(ActorEntity.class);
        ActorEntity entity = DBUtil.getDataByID(id,ActorEntity.class,s);
        return entity;
    }
    public List<ActorEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(ActorEntity.class);
        List<ActorEntity> ls = DBUtil.loadDataPagination( s,criteria);
            return Collections.unmodifiableList(ls);
    }
    public List<ActorEntity> loadTopRandom(Criteria criteria) {
        Session s = HibernateUtil.getSession(ActorEntity.class);
        List<ActorEntity> ls = DBUtil.getTopRandom(criteria, s);
        return Collections.unmodifiableList(ls);
    }
    public long count(){
        Session s = HibernateUtil.getSession(ActorEntity.class);
        return DBUtil.countData(s,ActorEntity.class);
    }
}
