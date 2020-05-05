package Server.model.DAO;

import Server.model.DB.CategorysongEntity;
import Server.model.DB.SongCategorysongEntity;
import Server.model.DB.SongEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(SongEntity.class).buildSessionFactory();
    public List<SongEntity> getAll() {
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.loadAllData(SongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SongEntity save(SongEntity entity){
        Session s = HibernateUtil.getSession(SongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(long id){
        Session s= HibernateUtil.getSession(SongEntity.class);
        DBUtil.deleteData(id,SongEntity.class,s);
    }
    public SongEntity getByID(long id){
        Session s = HibernateUtil.getSession(SongEntity.class);
        SongEntity entity = DBUtil.getDataByID(id,SongEntity.class,s);
        return entity;
    }
    public List<SongEntity> getSongByAuthorId(String conditionColumn,String condition ){
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.getListHasCondition("song",conditionColumn,condition,SongEntity.class,s);
        return Collections.unmodifiableList(ls);
    }
    public List<SongEntity> loadDataPagination(Server.model.DTO.Criteria criteria) {
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public long count(){
        Session s = HibernateUtil.getSession(SongEntity.class);
        return DBUtil.countDataWithCondition(s,SongEntity.class);
    }
    public List<SongEntity> getTop10(Criteria criteria){
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<SongEntity> getTop10New(Criteria criteria){
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.getTop10New("modifieddate",criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<SongEntity> getAll2() {
        Session s = HibernateUtil.getSession(SongEntity.class);
        List<SongEntity> ls = DBUtil.loadAllData(SongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
}
