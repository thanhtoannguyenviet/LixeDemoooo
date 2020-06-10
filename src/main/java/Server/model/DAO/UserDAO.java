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
    public List<UserEntity> getAll() {
        Session s = HibernateUtil.getSession(UserEntity.class);
        List<UserEntity> ls = DBUtil.loadAllData(UserEntity.class, s);
        return Collections.unmodifiableList(ls);
    }

    /**
     * User Register
     *
     * @param userEntity UserEntity
     * @return UserEntity
     */
    public UserEntity registerUser(UserEntity userEntity) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        return DBUtil.addData(userEntity, s);
    }

    /**
     * Get User by ID
     *
     * @param id long
     * @return UserEntity
     */
    public UserEntity getUserByID(long id) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        UserEntity user = new UserEntity();
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.getUserByID(id), s);
        if (listUsers != null && listUsers.size() != 0) {
            user = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
        }
        return user;
    }

    /**
     * Get All Users
     *
     * @return List<UserEntity>
     */
    public List<UserEntity> getAllUsers() {
        Session s = HibernateUtil.getSession(UserEntity.class);
        return DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.getAllUsers(), s);
    }
}
