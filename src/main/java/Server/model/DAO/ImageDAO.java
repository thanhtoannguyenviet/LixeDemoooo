package Server.model.DAO;

import Server.model.DB.FilmEntity;
import Server.model.DB.ImageEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class ImageDAO {
   public List<ImageEntity> getAll() {
        Session s = HibernateUtil.getSession(ImageEntity.class);
        List<ImageEntity> ls = DBUtil.loadAllData(ImageEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public ImageEntity save(ImageEntity entity){
        Session s = HibernateUtil.getSession(ImageEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(ImageEntity.class);
        DBUtil.deleteData(id,ImageEntity.class,s);
    }
    public ImageEntity getByID(Long id){
        Session s = HibernateUtil.getSession(ImageEntity.class);
        ImageEntity entity = DBUtil.getDataByID(id,ImageEntity.class,s);
        return entity;
    }
    public List<ImageEntity> getId(String model,long entryId ){
        Session s = HibernateUtil.getSession(ImageEntity.class);
        List<ImageEntity> ls = DBUtil.getImageOrResource("Image",model,entryId,ImageEntity.class,s);
        return ls;
    }
}
