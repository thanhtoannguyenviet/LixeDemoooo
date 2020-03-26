package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.AuthorEntity;
import Server.model.DB.UserEntity;
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
    public AuthorEntity Save(AuthorEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AuthorEntity.class,s);
    }
    public AuthorEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        AuthorEntity entity = DBUtil.GetDataByID(id,AuthorEntity.class,s);
        return entity;
    }
    public long GetId(String conditionColumn,String condition ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetId("Author",conditionColumn,condition);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(AuthorEntity.class);
            AuthorEntity authorEntity =(AuthorEntity) q.uniqueResult() ;
            return authorEntity.getId();
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return 0;
        } finally {
            s.close();
        }
    }
}
