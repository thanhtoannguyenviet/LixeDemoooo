package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DB.AlbumSongEntity;
import Server.model.DB.ApiaccountEntity;
import Server.service.DBUtil;
import Server.service.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class APIAccountDAO {

    /**
     * Check token<br>
     * @param apiToken String
     * @return apiTokenType int
     */
    public int checkToken(String apiToken) {
        if (apiToken == null || "".equalsIgnoreCase(apiToken)) return 0;

        Session s = HibernateUtil.getSession(ApiaccountEntity.class);
        List<ApiaccountEntity> entity = DBUtil.execCustomSQL(ApiaccountEntity.class, CUSTOM_QUERY.getToken(apiToken), s);
        if (entity != null && entity.size() != 0) {
            return DBUtil.convertToOBject(entity.get(0), ApiaccountEntity.class).getType();
        }

        return 0;
    }
}
