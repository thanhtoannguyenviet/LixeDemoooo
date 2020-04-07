package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.*;
import Server.model.DTO.UserDTO;
import Server.service.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserEntity.class).buildSessionFactory();
    public List<UserDTO> getAll() {
        Session s = factory.getCurrentSession();

        List<UserDTO> userList = new ArrayList<>();
        List<UserEntity> ls = DBUtil.loadAllData(UserEntity.class,s );
        for (UserEntity userEntity: ls
        ) {
            s = factory.getCurrentSession();
            RoleEntity role = DBUtil.GetDataByID(userEntity.getRoleid(),RoleEntity.class,s);
            UserDTO user = new UserDTO();
            user.setRoleEntity(role);
            user.setUserEntity(userEntity);


            s = factory.getCurrentSession();
            Transaction tx = s.beginTransaction();
            try{
                SQLQuery q = s.createSQLQuery(CUSTOM_QUERY.sqlImg(UserDTO.class.getName(), userEntity.getId()));
                q.setResultTransformer((Criteria.ALIAS_TO_ENTITY_MAP));

                List<ImageEntity> imageEntityList = (List<ImageEntity>) q.list();
                if(imageEntityList != null && imageEntityList.size() > 0){
                    user.setImageEntity(imageEntityList);
                }
            }catch (HibernateException ex) {
                if (tx != null) tx.rollback();
                ex.printStackTrace();
            } finally {
                s.close();
            }
            userList.add(user);
        }
        return Collections.unmodifiableList(userList);
    }
    public UserDTO login(String userName, String password){

        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try{
            SQLQuery q = s.createSQLQuery(CUSTOM_QUERY.sqlLogin(userName, password));
            q.addEntity(UserEntity.class);
            List<UserEntity> user = (List<UserEntity>) q.list();
            if(user != null && user.size() > 0){

                return GetAccountByID(user.get(0).getId());
            }
            return null;
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    public void Save(UserEntity entity){
        Session s = factory.getCurrentSession();
        DBUtil.addData(entity,s);
    }
    public void Delete(Long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,UserEntity.class,s);
    }
    public UserEntity GetByID(Long id){
        Session s = factory.getCurrentSession();
        UserEntity entity = DBUtil.GetDataByID(id,UserEntity.class,s);
        return entity;
    }

    public  UserEntity GetUserByUsername(String userName){
        Session s = factory.getCurrentSession();
        Transaction tx = s.beginTransaction();
        try {
            //sql = select * from User_ where userName = '?'
            String sql = CUSTOM_QUERY.GetUserByUsername;
            sql = sql.replace("?",userName);
            SQLQuery q = s.createSQLQuery(sql);
            q.addEntity(UserEntity.class);
            return (UserEntity) q.uniqueResult() ;
        }catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return null;
        } finally {
            s.close();
        }
    }

    public List<UserDTO> getAllAccount() {
        Session s = factory.getCurrentSession();

        List<UserDTO> userList = new ArrayList<>();
        List<UserEntity> ls = DBUtil.loadAllData(UserEntity.class, null);
        for (UserEntity userEntity: ls) {
            s = factory.getCurrentSession();
            RoleEntity role = DBUtil.GetDataByID(userEntity.getRoleid(),RoleEntity.class,s);
            UserDTO user = new UserDTO();
            user.setRoleEntity(role);
            user.setUserEntity(userEntity);

            s = factory.getCurrentSession();
            Transaction tx = s.beginTransaction();
            try{
                SQLQuery q = s.createSQLQuery(CUSTOM_QUERY.sqlImg(UserDTO.class.getName(), userEntity.getId()));
                q.setResultTransformer((Criteria.ALIAS_TO_ENTITY_MAP));

                List<ImageEntity> imageEntityList = (List<ImageEntity>) q.list();
                if(imageEntityList != null && imageEntityList.size() > 0){
                    user.setImageEntity(imageEntityList);
                }
            }catch (HibernateException ex) {
                if (tx != null) tx.rollback();
                ex.printStackTrace();
            } finally {
                s.close();
            }
            userList.add(user);
        }
        return Collections.unmodifiableList(userList);
    }
    public void CreateAccount(UserEntity acc){
        Session s = factory.getCurrentSession();
        DBUtil.addData(acc,s);
    }
    public void DeleteAccount(long id){
        Session s= factory.getCurrentSession();
        DBUtil.deleteData(id,UserEntity.class,s);
    }
    public UserDTO GetAccountByID(long id){
        Session s = factory.getCurrentSession();
        UserDTO user = new UserDTO();
        UserEntity acc = DBUtil.GetDataByID(id,UserEntity.class,s);
        user.setUserEntity(acc);
        s = factory.getCurrentSession();
        RoleEntity role = DBUtil.GetDataByID(acc.getRoleid(),RoleEntity.class,s);
        user.setRoleEntity(role);
        s = factory.getCurrentSession();
        SQLQuery q = s.createSQLQuery(CUSTOM_QUERY.sqlImg(UserDTO.class.getName(), acc.getId()));
        q.setResultTransformer((Criteria.ALIAS_TO_ENTITY_MAP));
        List<ImageEntity> imageEntity = (List<ImageEntity>) q.list();
        user.setImageEntity(imageEntity);

        return user;
    }

}
