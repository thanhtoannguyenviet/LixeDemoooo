package Server.model.DAO;

import Server.model.DB.SerifilmEntity;
import Server.model.DB.SingerEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
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
        Session s = factory.getCurrentSession();
        List<SingerEntity> ls = DBUtil.loadAllData(SingerEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SingerEntity Save(SingerEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SingerEntity.class,s);
    }
    public SingerEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        SingerEntity entity = DBUtil.GetDataByID(id,SingerEntity.class,s);
        return entity;
    }
    public List<SingerEntity> loadDataPagination(Criteria criteria) {
        Session s = factory.getCurrentSession();
        List<SingerEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public long count(){
        Session s = factory.getCurrentSession();
        return DBUtil.countDataWithCondition(s,SingerEntity.class);
    }
    public List<SingerEntity> GetTop10(Criteria criteria){
        Session s = factory.getCurrentSession();
        List<SingerEntity> ls = DBUtil.GetTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
}

