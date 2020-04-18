package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.service.DBUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AlbumCategoryMusicDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumCategorymusicEntity.class).buildSessionFactory();
    public List<AlbumCategorymusicEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AlbumCategorymusicEntity> ls = DBUtil.loadAllData(AlbumCategorymusicEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AlbumCategorymusicEntity save(AlbumCategorymusicEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AlbumCategorymusicEntity.class,s);
    }
    public AlbumCategorymusicEntity getByID(Long id){
        Session s = factory.getCurrentSession();
        AlbumCategorymusicEntity entity = DBUtil.getDataByID(id,AlbumCategorymusicEntity.class,s);
        return entity;
    }
    public List<AlbumCategorymusicEntity> getId(String conditionColumn,String condition ){
        Session s = factory.getCurrentSession();
        List<AlbumCategorymusicEntity> entity = DBUtil.getListHasCondition("album_categorymusic",conditionColumn,condition,AlbumCategorymusicEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
