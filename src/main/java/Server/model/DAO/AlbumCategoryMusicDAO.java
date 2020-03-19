package Server.model.DAO;

import Server.model.DB.AlbumCategoryMusicEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AlbumCategoryMusicDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumCategoryMusicEntity.class).buildSessionFactory();
    public List<AlbumCategoryMusicEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<AlbumCategoryMusicEntity> ls = DBUtil.loadAllData(AlbumCategoryMusicEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public void Save(AlbumCategoryMusicEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,AlbumCategoryMusicEntity.class,s);
    }
    public AlbumCategoryMusicEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        AlbumCategoryMusicEntity entity = DBUtil.GetDataByID(id,AlbumCategoryMusicEntity.class,s);
        return entity;
    }
}
