package Server.model.DAO;

import Server.model.DB.*;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class SearchDAO {
    public List<SearchEntity> getAll() {
        Session s = HibernateUtil.getSession(SearchEntity.class);
        List<SearchEntity> ls = DBUtil.loadAllData(SearchEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public SearchEntity Save(SearchEntity entity){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        return DBUtil.addData(entity,s);
    }
    public void Delete(int id){
        Session s= HibernateUtil.getSession(SearchEntity.class);
        DBUtil.deleteData(id,SearchEntity.class,s);
    }
    public SearchEntity GetByID(int id){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        SearchEntity entity = DBUtil.getDataByID(id,SearchEntity.class,s);
        return entity;
    }
    public List<SearchEntity> getSearch(String keyword, String model ){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        List<SearchEntity> ls = DBUtil.getSearch("Search",keyword,model,SearchEntity.class,s);
        return ls;
    }
    public void updateSearch(SearchEntity searchEntity){
        Session s = HibernateUtil.getSession(SearchEntity.class);
        DBUtil.addData(searchEntity,s);
    }
    public  <T> List<T> getSearchBasic(String keyword, String model ){
        Class clazz = null;
        String table=null;
        if(model.equals("film")) {
            table = "Film";
            clazz = FilmEntity.class;
        }else if(model.equals("song")){
            table = "Song";
            clazz = SongEntity.class;
        }else if(model.equals("actor")){
            table = "Actor";
            clazz = ActorEntity.class;
        }else if(model.equals("album")){
            table = "Album";
            clazz = AlbumEntity.class;
        }else if(model.equals("author")){
            table = "Author";
            clazz = AuthorEntity.class;
        }else if(model.equals("categoryfilm")){
            table = "CategoryFilm";
            clazz = CategoryfilmEntity.class;
        }else if(model.equals("categorysong")){
            table = "CategorySong";
            clazz = CategorysongEntity.class;
        }else if(model.equals("director")){
            table = "Director";
            clazz = DirectorEntity.class;
        }else if(model.equals("singer")){
            table = "Singer";
            clazz = SingerEntity.class;
        }
        Session s = HibernateUtil.getSession(clazz);
        List<T> ls = DBUtil.getDataByName(table,model+"Name",keyword, clazz,s);
        return ls;
    }
}
