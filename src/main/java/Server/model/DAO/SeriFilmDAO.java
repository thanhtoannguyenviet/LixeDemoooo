package Server.model.DAO;

import Server.model.DB.ActorEntity;
import Server.model.DB.SerifilmEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SeriFilmDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SerifilmEntity.class).buildSessionFactory();
    public List<SerifilmEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<SerifilmEntity> ls = DBUtil.loadAllData(SerifilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SerifilmEntity save(SerifilmEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,SerifilmEntity.class,s);
    }
    public SerifilmEntity getByID(long id){
        Session s = factory.getCurrentSession();
        SerifilmEntity entity = DBUtil.getDataByID(id,SerifilmEntity.class,s);
        return entity;
    }
    public List<SerifilmEntity> loadDataPagination(Criteria criteria) {
        Session s = factory.getCurrentSession();
        List<SerifilmEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public long count(){
        Session s = factory.getCurrentSession();
        return DBUtil.countData(s,SerifilmEntity.class);
    }
    public List<SerifilmEntity> getTop10(Criteria criteria){
        Session s = factory.getCurrentSession();
        List<SerifilmEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<SerifilmEntity> getTopRandom(Criteria criteria){
        Session s = factory.getCurrentSession();
        List<SerifilmEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
}
