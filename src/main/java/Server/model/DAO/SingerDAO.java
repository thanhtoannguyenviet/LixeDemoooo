package Server.model.DAO;

import Server.model.DB.SerifilmEntity;
import Server.model.DB.SingerEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SingerDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SingerEntity.class).buildSessionFactory();
    public List<SingerEntity> getAll() {
        Session s = HibernateUtil.getSession(SingerEntity.class);
        List<SingerEntity> ls = DBUtil.loadAllData(SingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SingerEntity save(SingerEntity entity){
        Session s = HibernateUtil.getSession(SingerEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(SingerEntity.class);
        DBUtil.deleteData(id,SingerEntity.class,s);
    }
    public SingerEntity getByID(Long id){
        Session s = HibernateUtil.getSession(SingerEntity.class);
        SingerEntity entity = DBUtil.getDataByID(id,SingerEntity.class,s);
        return entity;
    }
    public List<SingerEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(SingerEntity.class);
        List<SingerEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public long count(){
        Session s = HibernateUtil.getSession(SingerEntity.class);
        return DBUtil.countDataWithCondition(s,SingerEntity.class);
    }
    public List<SingerEntity> getTop10(Criteria criteria){
        Session s = HibernateUtil.getSession(SingerEntity.class);
        List<SingerEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<SingerEntity> getTopRandom(Criteria criteria){
        Session s = HibernateUtil.getSession(SingerEntity.class);
        List<SingerEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
}

