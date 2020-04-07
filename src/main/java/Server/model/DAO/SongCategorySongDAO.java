package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.SongCategorysongEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongCategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongCategorysongEntity.class).buildSessionFactory();
    public List<SongCategorysongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SongCategorysongEntity> ls = DBUtil.loadAllData(SongCategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(SongCategorysongEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SongCategorysongEntity.class,s);
    }
    public SongCategorysongEntity GetByID(long id){
        Session s = factory.getCurrentSession();
        SongCategorysongEntity entity = DBUtil.GetDataByID(id,SongCategorysongEntity.class,s);
        return entity;
    }
    public List<SongCategorysongEntity> GetId(String conditionColumn, String condition ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetId("Song_CategorySong",conditionColumn,condition);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(Server.model.DB.SongCategorysongEntity.class);
            return  q.getResultList() ;
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }
}
