package Server.model.DAO;

import Server.model.DB.AlbumEntity;
import Server.model.DB.AlbumSingerEntity;
import Server.model.DB.AlbumSongEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AlbumSongDAO {
    public List<AlbumSongEntity> getId(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(AlbumSongEntity.class);
        List<AlbumSongEntity> entity = DBUtil.getListHasCondition("album_song",conditionColumn,condition,AlbumSongEntity.class,s);
        return entity;
    }
    public AlbumSongEntity save(AlbumSongEntity entity){
        Session s = HibernateUtil.getSession(AlbumSongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AlbumSongEntity.class);
        DBUtil.deleteData(id,AlbumSongEntity.class,s);
    }
    public AlbumSongEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AlbumSongEntity.class);
        AlbumSongEntity entity = DBUtil.getDataByID(id,AlbumSongEntity.class,s);
        return entity;
    }
    public List<AlbumSongEntity> getId(Long condition, Long condition1 ){
        Session s = HibernateUtil.getSession(AlbumSongEntity.class);
        List<AlbumSongEntity> entity = DBUtil.getIdTableM2M("album_song","albumid",condition+"","songid",condition1+"",AlbumSongEntity.class,s);
        return entity;
    }
}
