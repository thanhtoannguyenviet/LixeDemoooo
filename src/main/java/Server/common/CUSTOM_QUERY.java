package Server.common;

public class CUSTOM_QUERY {
    public static String GetROLEByUsername =" from ROLE_ r, USER_ where roleId = ?";
    public static String GetUserByUsername = "select top 1 * from User_ u where userName like '?'";
    public static String sqlImg(String model, long entryId){
        String sql = "SELECT * FROM Image WHERE model = '" + model+ "' AND entryId = " + entryId + " ORDER BY id desc ";
        return sql;
    }

    public static String sqlUpload(String model, long entryId){
        String sql = "SELECT * FROM Upload WHERE model = '" +  model + "' AND entryId = " + entryId + " ORDER BY id desc ";
        return sql;
    }

    public static String sqlLogin(String userName, String password){
        String sql = "SELECT * FROM User_ WHERE userName = '" +  userName + "' AND password = '" + password + "' ";
        return sql;
    }
    public static String sqlUserDetailService(String s){
        String sql = "select userName,password,roleName from User_, Role_ where User_.roleId=Role_.id and userName ='"+s+"'";
        return sql;
    }
    public static String sqlGetId(String model,String conditionColumn,String condition ){
        String sql = "Select * from"+ model + "Where" + conditionColumn + "='"+condition+"'";
        return sql;
    }
    public static String sqlGetIdFromImageOrResource(String table,String model,long entryId ){
        String sql = "Select * from"+ table + "Where model ='" + model + "'and entryId='"+entryId+"'";
        return sql;
    }
}
