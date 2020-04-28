package Server.model.DAO;

import Server.model.DB.AlbumEntity;
import Server.model.DB.AlbumSingerEntity;
import Server.model.DB.AlbumSongEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AlbumSongDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(AlbumSongEntity.class).buildSessionFactory();
    public List<AlbumSongEntity> getId(String conditionColumn, String condition ){
        Session s = factory.getCurrentSession();
        List<AlbumSongEntity> entity = DBUtil.getListHasCondition("album_song",conditionColumn,condition,AlbumSongEntity.class,s);
        return entity;
    }
}
