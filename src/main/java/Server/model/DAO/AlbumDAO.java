package Server.model.DAO;

import Server.model.DB.AlbumCategorymusicEntity;
import Server.model.DB.AlbumEntity;
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
public class AlbumDAO {
    public List<AlbumEntity> getAll() {
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        List<AlbumEntity> ls = DBUtil.loadAllData(AlbumEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AlbumEntity save(AlbumEntity entity){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AlbumEntity.class);
        DBUtil.deleteData(id,AlbumEntity.class,s);
    }
    public AlbumEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        AlbumEntity entity = DBUtil.getDataByID(id,AlbumEntity.class,s);
        return entity;
    }
    public long count(){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        return DBUtil.countData(s,AlbumEntity.class);
    }
    public List<AlbumEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        List<AlbumEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public List<AlbumEntity> getTop10(Criteria criteria){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        List<AlbumEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<AlbumEntity> getTopRandom(Criteria criteria){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        List<AlbumEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<AlbumEntity> getTopNew(Criteria criteria){
        Session s = HibernateUtil.getSession(AlbumEntity.class);
        List<AlbumEntity> ls = DBUtil.getTop10New("datereleased",criteria,s);
        return Collections.unmodifiableList(ls);
    }

}
