package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.SongEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongEntity.class).buildSessionFactory();
    public List<SongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SongEntity> ls = DBUtil.loadAllData(SongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(SongEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SongEntity.class,s);
    }
    public SongEntity GetByID(long id){
        Session s = factory.getCurrentSession();
        SongEntity entity = DBUtil.GetDataByID(id,SongEntity.class,s);
        return entity;
    }
    public long GetId(String conditionColumn,String condition ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetId("Song",conditionColumn,condition);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(SongEntity.class);
            SongEntity entity =(SongEntity) q.uniqueResult() ;
            return entity.getId();
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return 0;
        } finally {
            s.close();
        }
    }
}
