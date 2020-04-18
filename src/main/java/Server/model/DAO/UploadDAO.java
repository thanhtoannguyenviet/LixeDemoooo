package Server.model.DAO;

import Server.model.DB.ImageEntity;
import Server.model.DB.UploadEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class UploadDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UploadEntity.class).buildSessionFactory();
    public List<UploadEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<UploadEntity> ls = DBUtil.loadAllData(UploadEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public UploadEntity save(UploadEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,UploadEntity.class,s);
    }
    public UploadEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        UploadEntity entity = DBUtil.getDataByID(id,UploadEntity.class,s);
        return entity;
    }
    public List<UploadEntity> getId(String model, long entryId ){
        Session s = factory.getCurrentSession();
        List<UploadEntity> ls = DBUtil.getImageOrResource("Upload",model,entryId,UploadEntity.class,s);
        return Collections.unmodifiableList(ls);
    }
}
