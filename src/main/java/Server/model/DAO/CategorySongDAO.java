package Server.model.DAO;

import Server.model.DB.CategoryfilmEntity;
import Server.model.DB.CategorysongEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static Server.service.DBUtil.addData;

@Repository
public class CategorySongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CategorysongEntity.class).buildSessionFactory();
    public List<CategorysongEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<CategorysongEntity> ls = DBUtil.loadAllData(CategorysongEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public CategorysongEntity save(CategorysongEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,CategorysongEntity.class,s);
    }
    public CategorysongEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        CategorysongEntity entity = DBUtil.getDataByID(id,CategorysongEntity.class,s);
        return entity;
    }
    public long count(){
        Session s =factory.getCurrentSession();
        return DBUtil.countData(s, CategorysongEntity.class);
    }
    public List<CategorysongEntity> loadDataPagination(Criteria criteria) {
        Session s = factory.getCurrentSession();
        List<CategorysongEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }
    public List<CategorysongEntity> getTop10(Criteria criteria){
        Session s = factory.getCurrentSession();
        List<CategorysongEntity> ls = DBUtil.getTop10(criteria,s);
        return Collections.unmodifiableList(ls);
    }
    public List<CategorysongEntity> getTopRandom(Criteria criteria){
        Session s = factory.getCurrentSession();
        List<CategorysongEntity> ls = DBUtil.getTopRandom(criteria,s);
        return Collections.unmodifiableList(ls);
    }
}
