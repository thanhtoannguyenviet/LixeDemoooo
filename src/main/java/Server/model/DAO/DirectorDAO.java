package Server.model.DAO;

import Server.model.DB.CategorysongEntity;
import Server.model.DB.DirectorEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class DirectorDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(DirectorEntity.class).buildSessionFactory();
    public List<DirectorEntity> getAll() {
        Session s = factory.getCurrentSession();
        List<DirectorEntity> ls = DBUtil.loadAllData(DirectorEntity.class, s);
        return Collections.unmodifiableList(ls);
    }
    public DirectorEntity Save(DirectorEntity entity){
        Session s = factory.getCurrentSession();
        return DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,DirectorEntity.class,s);
    }
    public DirectorEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        DirectorEntity entity = DBUtil.GetDataByID(id,DirectorEntity.class,s);
        return entity;
    }
    public long count(){
        Session s =factory.getCurrentSession();
        return DBUtil.countData(s, DirectorEntity.class);
    }
    public List<DirectorEntity> loadDataPagination(Criteria criteria) {
        Session s = factory.getCurrentSession();
        List<DirectorEntity> ls = DBUtil.loadDataPagination( s,criteria);
        return Collections.unmodifiableList(ls);
    }

}
