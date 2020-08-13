package Server.model.DAO;

import Server.model.DB.ActorEntity;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class AlbumCategoryMusicDAO {
   public List<AlbumCategorymusicEntity> getAll() {
        Session s = HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        List<AlbumCategorymusicEntity> ls = DBUtil.loadAllData(AlbumCategorymusicEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public AlbumCategorymusicEntity save(AlbumCategorymusicEntity entity){
        Session s = HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        DBUtil.deleteData(id,AlbumCategorymusicEntity.class,s);
    }
    public AlbumCategorymusicEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        AlbumCategorymusicEntity entity = DBUtil.getDataByID(id,AlbumCategorymusicEntity.class,s);
        return entity;
    }
    public List<AlbumCategorymusicEntity> getId(String conditionColumn,String condition ){
        Session s = HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        List<AlbumCategorymusicEntity> entity = DBUtil.getListHasCondition("album_categorymusic",conditionColumn,condition,AlbumCategorymusicEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
    public List<AlbumCategorymusicEntity> getId(Long condition,Long condition1 ){
        Session s = HibernateUtil.getSession(AlbumCategorymusicEntity.class);
        List<AlbumCategorymusicEntity> entity = DBUtil.getIdTableM2M("album_categorymusic","albumId",condition+"","cateoryId",condition1+"",AlbumCategorymusicEntity.class,s);
        return Collections.unmodifiableList(entity);
    }
}
