package Server.model.DAO;

import Server.model.DB.SongEntity;
import Server.model.DB.UploadEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class UploadDAO {
    public List<UploadEntity> getAll() {
        Session s = HibernateUtil.getSession(UploadEntity.class);
        List<UploadEntity> ls = DBUtil.loadAllData(UploadEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public UploadEntity save(UploadEntity entity){
        Session s = HibernateUtil.getSession(SongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s = HibernateUtil.getSession(UploadEntity.class);
        DBUtil.deleteData(id,UploadEntity.class,s);
    }
    public UploadEntity getByID(Long id){
        Session s = HibernateUtil.getSession(UploadEntity.class);
        UploadEntity entity = DBUtil.getDataByID(id,UploadEntity.class,s);
        return entity;
    }
    public List<UploadEntity> getId(String model, long entryId ){
        Session s = HibernateUtil.getSession(UploadEntity.class);
        List<UploadEntity> ls = DBUtil.getImageOrResource("Upload",model,entryId,UploadEntity.class,s);
        return Collections.unmodifiableList(ls);
    }
}
