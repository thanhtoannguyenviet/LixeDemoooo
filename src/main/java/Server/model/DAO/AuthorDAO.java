package Server.model.DAO;

import Server.model.DB.AlbumSongEntity;
import Server.model.DB.AuthorEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AuthorDAO {
    public List<AuthorEntity> getAll() {
        Session s = HibernateUtil.getSession(AuthorEntity.class);
        List<AuthorEntity> ls = DBUtil.loadAllData(AuthorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AuthorEntity save(AuthorEntity entity){
        Session s = HibernateUtil.getSession(AuthorEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AuthorEntity.class);
        DBUtil.deleteData(id,AuthorEntity.class,s);
    }
    public  AuthorEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AuthorEntity.class);
        AuthorEntity entity = DBUtil.getDataByID(id,AuthorEntity.class,s);
        return entity;
    }
    public long count(){
        Session s = HibernateUtil.getSession(AuthorEntity.class);
        return DBUtil.countData(s, AuthorEntity.class);
    }
    public List<AuthorEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(AuthorEntity.class);
        List<AuthorEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
}
