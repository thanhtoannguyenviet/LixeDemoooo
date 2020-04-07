package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.ImageEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class ImageDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ImageEntity.class).buildSessionFactory();
    public List<ImageEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<ImageEntity> ls = DBUtil.loadAllData(ImageEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(ImageEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,ImageEntity.class,s);
    }
    public ImageEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        ImageEntity entity = DBUtil.GetDataByID(id,ImageEntity.class,s);
        return entity;
    }
    public List<ImageEntity> GetId(String model,long entryId ){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.sqlGetIdFromImageOrResource("Image",model,entryId);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(ImageEntity.class);
            ImageEntity imageEntity =(ImageEntity) q.uniqueResult() ;
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
