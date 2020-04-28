package Server.model.DAO;

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
    public ImageEntity save(ImageEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,ImageEntity.class,s);
    }
    public ImageEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        ImageEntity entity = DBUtil.getDataByID(id,ImageEntity.class,s);
        return entity;
    }
    public List<ImageEntity> getId(String model,long entryId ){
        Session s = factory.getCurrentSession();
        List<ImageEntity> ls = DBUtil.getImageOrResource("Image",model,entryId,ImageEntity.class,s);
        return Collections.unmodifiableList(ls);
    }
}
