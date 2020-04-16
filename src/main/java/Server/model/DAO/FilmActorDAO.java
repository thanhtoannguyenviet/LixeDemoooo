package Server.model.DAO;

import Server.model.DB.FilmActorEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class FilmActorDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(FilmActorEntity.class).buildSessionFactory();
    public List<FilmActorEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<FilmActorEntity> ls = DBUtil.loadAllData(FilmActorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public FilmActorEntity Save(FilmActorEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,FilmActorEntity.class,s);
    }
    public FilmActorEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        FilmActorEntity entity = DBUtil.GetDataByID(id,FilmActorEntity.class,s);
        return entity;
    }
}
