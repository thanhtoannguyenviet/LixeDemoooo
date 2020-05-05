package Server.model.DAO;

import Server.model.DB.CommentEntity;
import Server.model.DB.DirectorEntity;
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
public class DirectorDAO {
    public List<DirectorEntity> getAll() {
        Session s = HibernateUtil.getSession(DirectorEntity.class);
        List<DirectorEntity> ls = DBUtil.loadAllData(DirectorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public DirectorEntity save(DirectorEntity entity){
        Session s = HibernateUtil.getSession(DirectorEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(DirectorEntity.class);
        DBUtil.deleteData(id,DirectorEntity.class,s);
    }
    public DirectorEntity getByID(Long id){
        Session s = HibernateUtil.getSession(DirectorEntity.class);
        DirectorEntity entity = DBUtil.getDataByID(id,DirectorEntity.class,s);
        return entity;
    }
    public long count(){
        Session s = HibernateUtil.getSession(DirectorEntity.class);
        return DBUtil.countData(s, DirectorEntity.class);
    }
    public List<DirectorEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(DirectorEntity.class);
        List<DirectorEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
}
