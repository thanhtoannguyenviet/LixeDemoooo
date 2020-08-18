package Server.common;

import Server.model.DB.FilmEntity;
import Server.model.DB.SongEntity;
import Server.model.DB.UserEntity;
import Server.model.DTO.Criteria;
import Server.model.DTO.UserDTO;

import java.util.List;

public class CUSTOM_QUERY {
    public static String GetROLEByUsername = " from ROLE_ r, USER_ where roleId = ?";
    public static String GetUserByUsername = "select top 1 * from User_ u where userName like '?'";

    public static String sqlImg(String model, long entryId) {
        String sql = "SELECT * FROM Image WHERE model = '" + model + "' AND entryId = " + entryId + " ORDER BY id desc ";
        return sql;
    }

    public static String sqlUpload(String model, long entryId) {
        String sql = "SELECT * FROM Upload WHERE model = '" + model + "' AND entryId = " + entryId + " ORDER BY id desc ";
        return sql;
    }

    public static String sqlLogin(String userName, String password) {
        String sql = "SELECT * FROM User_ WHERE userName = '" + userName + "' AND password = '" + password + "' ";
        return sql;
    }

    public static String sqlUserDetailService(String s) {
        String sql = "select userName,password,roleName from User_, Role_ where User_.roleId=Role_.id and userName ='" + s + "'";
        return sql;
    }

    public static String sqlGetId(String table, String conditionColumn, String condition) {
        String sql = "Select * from " + table + " Where " + conditionColumn + " ='" + condition + "'";
        return sql;
    }

    public static String sqlGetIdFromImageOrResource(String table, String model, long entryId) {
        String sql = "Select * from " + table + " Where model ='" + model + "'and entryId='" + entryId + "'";
        return sql;
    }
    public static String sqlGetSearch(String table, String keyword , String model) {
        String sql = "Select top 1 * from " + table + " Where keyword like N'%" + keyword.toLowerCase() + "%'and model like '"+model+"'";
        return sql;
    }

    public static String getIdTableM2M(String table, String conditionColumn, String condition,String conditionColumn1, String condition1){
        String sql = "select * from "+ table + " where "+ conditionColumn+ " =" + condition + " and "+ conditionColumn1 + " = "+ condition1;
        return sql;
    }
    public static String sqlGetTop(Criteria criteria) {
        String model = "";
        if (criteria.getClazz().equals(SongEntity.class)) {
            model = "Song";
        }
        if (criteria.getClazz().equals(FilmEntity.class)) {
            model = "Film";
        }
        String sql = "SELECT * FROM " + model + " ORDER BY range desc LIMIT " + criteria.getTop();
        return sql;
    }


    public static String searchBasic(String table, String conditionColumn,String condition){
        String sql = "select * from " +table+" where "+ conditionColumn+" like N'%"+condition+"%' order by "+conditionColumn ;
        return sql;
    }

    /*----- Start USER/ACCOUNT ----- */
    public static String getToken(String string) {
        String sql = "SELECT * FROM APIAccount WHERE token ='" + string + "'";
        return sql;
    }

    public static String getUserByID(long id) {
        String sql = "SELECT id, username, '' password, email, ext, follow, active, roleid, img, displayname, createDate, createUser, updateDate, updateUser, userWebToken, webTokenCreateDate, userMbToken, mbTokenCreateDate FROM User_ WHERE id = '" + id + "'";
        return sql;
    }

    public static String getUserByName(String username) {
        String sql = "SELECT id, username, '' password, email, ext, follow, active, roleid, img, displayname, createDate, createUser, updateDate, updateUser, userWebToken, webTokenCreateDate, userMbToken, mbTokenCreateDate FROM USER_ WHERE username = '" + username + "'";
        return sql;
    }

    public static String checkUniqueUsername(String username) {
        String sql = "SELECT * FROM USER_ WHERE username = '" + username + "'";
        return sql;
    }

    public static String findUserByToken(String userToken, int type) {
        String whereCondition = "WHERE ";
        if (type == 1) {
            whereCondition += "userWebToken = '" + userToken + "'";
        } else {
            whereCondition += "userMbToken = '" + userToken + "'";
        }
        String sql = "SELECT id, username, password, email, ext, follow, active, roleid, img, displayname, " +
                "createDate, createUser, updateDate, updateUser, userWebToken, webTokenCreateDate, userMbToken, mbTokenCreateDate " +
                "FROM User_ " + whereCondition;
        return sql;
    }

    public static String findNextIdentity(String tableName) {
        String sql = "SELECT COALESCE(IDENT_CURRENT('" + tableName + "'), 0) + 1 id FROM USER_";
        return sql;
    }

    public static String loginAccount(UserEntity user) {
        String sql = "SELECT id, username, password, email, ext, follow, active, roleid, img, displayname, " +
                "createDate, createUser, updateDate, updateUser, userWebToken, webTokenCreateDate, userMbToken, mbTokenCreateDate " +
                "FROM User_ WHERE username = '" + user.getUsername() + "'";
        return sql;
    }
    /*----- End USER/ACCOUNT ----- */
}