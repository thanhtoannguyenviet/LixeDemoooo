package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.AlbumSongEntity;
import Server.model.DB.ApiaccountEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class APIAccountDAO {

    public int checkToken( String condition ){
        Session s = HibernateUtil.getSession(ApiaccountEntity.class);
        List<ApiaccountEntity> entity = DBUtil.execCustomSQL(ApiaccountEntity.class, CUSTOM_QUERY.getToken(condition),s);;
        return DBUtil.convertToOBject( entity.get(0),ApiaccountEntity.class).getType();
    }
}
