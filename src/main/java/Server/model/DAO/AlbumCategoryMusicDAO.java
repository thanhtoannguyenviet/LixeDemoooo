package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AlbumCategoryMusicDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumCategorymusicEntity.class).buildSessionFactory();
    public List<AlbumCategorymusicEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AlbumCategorymusicEntity> ls = DBUtil.loadAllData(AlbumCategorymusicEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(AlbumCategorymusicEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AlbumCategorymusicEntity.class,s);
    }
    public AlbumCategorymusicEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        AlbumCategorymusicEntity entity = DBUtil.GetDataByID(id,AlbumCategorymusicEntity.class,s);
        return entity;
    }
    public List<AlbumCategorymusicEntity> GetId(String conditionColumn,String condition ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetId("album_categorymusic",conditionColumn,condition);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(AlbumCategorymusicEntity.class);
            return q.getResultList();
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }
}
