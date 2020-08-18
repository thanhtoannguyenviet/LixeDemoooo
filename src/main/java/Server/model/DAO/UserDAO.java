package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.*;
import Server.model.DTO.UserDTO;
import Server.model.DTO.UserInDTO;
import Server.service.*;
import org.hibernate.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    RoleDAO roleDAO = new RoleDAO();

    public String bCryptPassword(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> listRtn = new ArrayList<UserDTO>();
        List<UserEntity> listUsers = getAllUser_();

        for (UserEntity uE : listUsers) {
            UserDTO uD = new UserDTO();
            uE.setPassword("");
            RoleEntity rE = roleDAO.getByID(uE.getId());
            uD.setUserEntity(uE);
            uD.setRoleEntity(rE);
            listRtn.add(uD);
        }
        return listRtn;
    }

    /**
     * Internal use
     * @return List<UserEntity>
     */
    public List<UserEntity> getAllUser_() {
        Session s = HibernateUtil.getSession(UserEntity.class);
        List<UserEntity> ls = DBUtil.loadAllData(UserEntity.class, s);
        return Collections.unmodifiableList(ls);
    }

    public UserEntity registerUser(UserEntity entity) {
        UserEntity uE = null;
        Session s = HibernateUtil.getSession(UserEntity.class);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        entity.setActive(true);
        entity.setCreateDate(currentTimestamp);
        entity.setUpdateDate(currentTimestamp);
        entity.setCreateUser(searchMaxIdentity());
        entity.setPassword(bCryptPassword(entity.getPassword()));

        int cnt = DBUtil.addData2(entity, s);
        if (cnt == 1) {
            uE = entity;
        }
        return uE;
    }

    /**
     * Check if the Username is unique<br>
     * @param username String
     * @return boolean
     */
    public boolean checkUniqueUsername(String username) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        List<UserEntity> entity = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.checkUniqueUsername(username), s);
        if (entity != null && entity.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * Get User by ID
     *
     * @param id long
     * @return UserEntity
     */
    public UserDTO getUserByID(long id) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        UserDTO rtnUser = new UserDTO();
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.getUserByID(id), s);
        if (listUsers != null && listUsers.size() > 0) {
            UserEntity user = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
            rtnUser.setUserEntity(user);
            rtnUser.setRoleEntity(roleDAO.getByID(user.getRoleid()));
        }
        return rtnUser;
    }

    public long searchMaxIdentity() {
        Session s = HibernateUtil.getSession(UserEntity.class);
        long rtn = 0;
        List<UserEntity> result = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.findNextIdentity("User_"), s);
        if (result != null && result.size() > 0) {
            rtn = DBUtil.convertToOBject(result.get(0), UserEntity.class).getId();
        }
        return rtn;
    }

    /**
     * Get User by String
     *
     * @param username String
     * @return UserDTO
     */
    public UserDTO getUserByName(String username) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        UserDTO rtnUser = new UserDTO();
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.getUserByName(username), s);
        if (listUsers != null && listUsers.size() > 0) {
            UserEntity user = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
            rtnUser.setUserEntity(user);
            rtnUser.setRoleEntity(roleDAO.getByID(user.getRoleid()));
        }
        return rtnUser;
    }

    /**
     * Find User by Token<br>
     * @param token String
     * @param type int API Token Type (1: Web, 2: Mobile)
     * @return UserEntity
     */
    public UserEntity findUserByToken(String token, int type) {
        Session s = HibernateUtil.getSession(UserEntity.class);
        UserEntity uE = null;
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.findUserByToken(token, type), s);
        if (listUsers != null && listUsers.size() > 0) {
            uE = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
        }
        return uE;
    }

    /**
     * Create Token
     * @param user UserEntity
     * @param type int API Token Type (1: Web, 2: Mobile)
     * @return String token
     */
    public String createToken(UserEntity user, int type) {
        String token = "";
        UserEntity uE = null;
        Session s = HibernateUtil.getSession(UserEntity.class);
        String uniqueId = UUID.randomUUID().toString().replace("-","");
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        if (type == 1) {
            user.setUserWebToken(uniqueId);
            user.setWebTokenCreateDate(currentTimestamp);
        } else {
            user.setUserMbToken(uniqueId);
            user.setMbTokenCreateDate(currentTimestamp);
        }

        int cnt = DBUtil.updateData(user, s); // UPDATE USER_
        if (cnt == 1) {
            token = uniqueId;
        }
        return token;
    }

    /**
     * Basically search by Username & Password.
     * @param user UserEntity
     * @return UserDTO
     */
    public UserDTO login(UserEntity user, int type) {
        UserDTO uD = new UserDTO();
        Session s = HibernateUtil.getSession(UserEntity.class);
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.loginAccount(user), s);
        if (listUsers != null && listUsers.size() > 0) { // Account exists
            UserEntity uE = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
            if (bCryptPasswordEncoder.matches(user.getPassword(), uE.getPassword())) { // Right Password
                if (type == 1 && (uE.getUserWebToken() == null || "".equalsIgnoreCase(uE.getUserWebToken()))) {
                    uE.setUserWebToken(createToken(uE, type));
                } else if (type == 2) { // Login from Mobile => Reset MbToken
                    uE.setUserMbToken(createToken(uE, type));
                }
                uE.setPassword("");
                uD.setUserEntity(uE);
                uD.setRoleEntity(roleDAO.getByID(uE.getRoleid()));
            }
        }
        return uD;
    }

    /**
     * Quick search User Role<br>
     * @param userToken String
     * @param aipTokenType int (1: WEB, 2: MOBILE)
     * @return roleId long (1: ADMIN, 2: MEMBER)
     */
    public long checkUserRoleId(String userToken, int aipTokenType) {
        UserEntity uE = findUserByToken(userToken, aipTokenType);
        if (uE != null) {
            return uE.getRoleid();
        }
        return 0;
    }

    /**
     * Change Password
     * @param userInDTO UserInDTO
     * @return int
     */
    public int changePassword(UserInDTO userInDTO) {
        int rtn = 0;
        Session s = HibernateUtil.getSession(UserEntity.class);
        List<UserEntity> listUsers = DBUtil.execCustomSQL(UserEntity.class, CUSTOM_QUERY.loginAccount(userInDTO.getUserEntity()), s);
        if (listUsers != null && listUsers.size() > 0) { // Account exists
            UserEntity uE = DBUtil.convertToOBject(listUsers.get(0), UserEntity.class);
            if (bCryptPasswordEncoder.matches(userInDTO.getUserEntity().getPassword(), uE.getPassword())) { // Right Password
                // Update new Password
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                Session s2 = HibernateUtil.getSession(UserEntity.class);
                uE.setPassword(userInDTO.getNewPassword());
                uE.setUpdateDate(currentTimestamp);
                uE.setUpdateUser(uE.getId());
                uE.setUserWebToken("");
                uE.setWebTokenCreateDate(currentTimestamp);
                uE.setUserMbToken("");
                uE.setMbTokenCreateDate(currentTimestamp);

                rtn = DBUtil.updateData(uE, s); // UPDATE USER_
            }
        }

        return rtn;
    }
}
