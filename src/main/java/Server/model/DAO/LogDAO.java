package Server.model.DAO;

import Server.model.DB.ImageEntity;
import Server.model.DB.LogEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class LogDAO {
    public void save(LogEntity entity){
        Session s = HibernateUtil.getSession(LogEntity.class);
        DBUtil.addData(entity,s);
    }
}
