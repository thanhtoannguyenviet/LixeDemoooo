package Server.model.DAO;

import Server.model.DB.CategoryfilmEntity;
import Server.model.DB.CategorysongEntity;
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
public class CategorySongDAO {
    public List<CategorysongEntity> getAll() {
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        List<CategorysongEntity> ls = DBUtil.loadAllData(CategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public CategorysongEntity save(CategorysongEntity entity){
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(CategorysongEntity.class);
        DBUtil.deleteData(id,CategorysongEntity.class,s);
    }
    public CategorysongEntity getByID(Long id){
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        CategorysongEntity entity = DBUtil.getDataByID(id,CategorysongEntity.class,s);
        return entity;
    }
    public long count(){
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        return DBUtil.countData(s, CategorysongEntity.class);
    }
    public List<CategorysongEntity> loadDataPagination(Criteria criteria) {
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        List<CategorysongEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public List<CategorysongEntity> getTop10(Criteria criteria){
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        List<CategorysongEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<CategorysongEntity> getTopRandom(Criteria criteria){
        Session s = HibernateUtil.getSession(CategorysongEntity.class);
        List<CategorysongEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
}
