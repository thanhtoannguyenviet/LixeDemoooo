package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;

public class SignalDAO {

    public static <K> K findData(Criteria criteria){
        if(criteria != null){
            if(criteria.getTop() >0){
                DBUtil.execCustomSQL(criteria.getClazz(), CUSTOM_QUERY.sqlGetTop(criteria));
            }
            if(criteria.getClazz()!= null){
                DBUtil.loadDataPagination(criteria.getClazz(),criteria);
            }

        }
        DBUtil.convertToOBject(null, criteria.getClazz());
        return null;
    }
}
