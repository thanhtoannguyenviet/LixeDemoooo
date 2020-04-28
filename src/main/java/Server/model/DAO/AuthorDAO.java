package Server.model.DAO;

import Server.model.DB.AuthorEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AuthorDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AuthorEntity.class).buildSessionFactory();
    public List<AuthorEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AuthorEntity> ls = DBUtil.loadAllData(AuthorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AuthorEntity save(AuthorEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AuthorEntity.class,s);
    }
    public  AuthorEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        AuthorEntity entity = DBUtil.getDataByID(id,AuthorEntity.class,s);
        return entity;
    }
    public long count(){
        Session s =factory.getCurrentSession();
        return DBUtil.countData(s, AuthorEntity.class);
    }
    public List<AuthorEntity> loadDataPagination(Criteria criteria) {
        Session s = factory.getCurrentSession();
        List<AuthorEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
}
