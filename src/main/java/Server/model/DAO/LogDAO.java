package Server.model.DAO;

import Server.model.DB.ImageEntity;
import Server.model.DB.LogEntity;
import Server.service.DBUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class LogDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(LogEntity.class).buildSessionFactory();
    public void Save(LogEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
}
