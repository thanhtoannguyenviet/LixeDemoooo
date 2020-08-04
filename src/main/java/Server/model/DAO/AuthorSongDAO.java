package Server.model.DAO;

import Server.model.DB.AlbumSongEntity;
import Server.model.DB.AuthorSongEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AuthorSongDAO {
    public List<AuthorSongEntity> getId(String conditionColumn, String condition ){
        Session s = HibernateUtil.getSession(AuthorSongEntity.class);
        List<AuthorSongEntity> entity = DBUtil.getListHasCondition("author_song",conditionColumn,condition,AuthorSongEntity.class,s);
        return entity;
    }
    public AuthorSongEntity save(AuthorSongEntity entity){
        Session s = HibernateUtil.getSession(AuthorSongEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void delete(Long id){
        Session s= HibernateUtil.getSession(AuthorSongEntity.class);
        DBUtil.deleteData(id,AuthorSongEntity.class,s);
    }
    public AuthorSongEntity getByID(Long id){
        Session s = HibernateUtil.getSession(AuthorSongEntity.class);
        AuthorSongEntity entity = DBUtil.getDataByID(id,AuthorSongEntity.class,s);
        return entity;
    }
    public List<AuthorSongEntity> getId(Long condition, Long condition1 ){
        Session s = HibernateUtil.getSession(AuthorSongEntity.class);
        List<AuthorSongEntity> entity = DBUtil.getIdTableM2M("author_song","authorid",condition+"","songid",condition1+"",AuthorSongEntity.class,s);
        return entity;
    }
}
