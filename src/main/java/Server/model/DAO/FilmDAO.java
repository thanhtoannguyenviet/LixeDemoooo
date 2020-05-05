package Server.model.DAO;

import Server.model.DB.FilmCategoryfilmEntity;
import Server.model.DB.FilmEntity;
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
public class FilmDAO {
   public List<FilmEntity> getAll() {
        Session s = HibernateUtil.getSession(FilmEntity.class);
        List<FilmEntity> ls = DBUtil.loadAllData(FilmEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public FilmEntity save(FilmEntity entity){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(FilmEntity.class);
        DBUtil.deleteData(id,FilmEntity.class,s);
    }
    public FilmEntity getByID(Long id){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        FilmEntity entity = DBUtil.getDataByID(id,FilmEntity.class,s);
        return entity;
    }
    public long count(){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        return DBUtil.countDataWithCondition(s, FilmEntity.class);
    }
    public List<FilmEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(FilmEntity.class);
        List<FilmEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public List<FilmEntity> GetTop10(Criteria criteria){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        List<FilmEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<FilmEntity> getTopRandom(Criteria criteria){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        List<FilmEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<FilmEntity> getTopNew(Criteria criteria){
        Session s = HibernateUtil.getSession(FilmEntity.class);
        List<FilmEntity> ls = DBUtil.getTop10New("createdate",criteria,s);
        return Collections.unmodifiableList(ls);
    }
}
