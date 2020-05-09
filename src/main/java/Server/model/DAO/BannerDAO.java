package Server.model.DAO;

import Server.model.DB.BannerEntity;
import Server.model.DB.CategoryfilmEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class BannerDAO {
    public List<BannerEntity> getAll() {
        Session s = HibernateUtil.getSession(BannerEntity.class);
        List<BannerEntity> ls = DBUtil.loadAllData(BannerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public BannerEntity save(BannerEntity entity){
        Session s = HibernateUtil.getSession(BannerEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(BannerEntity.class);
        DBUtil.deleteData(id,BannerEntity.class,s);
    }
    public BannerEntity getByID(Long id){
        Session s = HibernateUtil.getSession(BannerEntity.class);
        BannerEntity entity = DBUtil.getDataByID(id,BannerEntity.class,s);
        return entity;
    }
}
