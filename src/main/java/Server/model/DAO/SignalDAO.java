package Server.model.DAO;

import Server.common.CUSTOM_QUERY;
import Server.common.CommonUtil;
import Server.model.DB.ImageEntity;
import Server.model.DB.UploadEntity;
import Server.model.DTO.Criteria;
import Server.service.DBUtil;

import java.util.List;

public class SignalDAO {

    public static <K> List<K> findData(Criteria criteria) throws Exception {
        if (null == criteria.getClazz()){
            throw new ClassNotFoundException();
        }
        if(criteria != null){
            if(criteria.getClazz()!= null){
                if(criteria.getTop() >0){
                    DBUtil.execCustomSQL(criteria.getClazz(), CUSTOM_QUERY.sqlGetTop(criteria));
                }
                else{
                    DBUtil.loadDataPagination(criteria.getClazz(),criteria);
                }
            }


        }
        return null;
    }

    public long countData(Criteria criteria) throws Exception {
        if (null == criteria.getClazz()){
            throw new ClassNotFoundException();
        }
        if(criteria != null){
            if(criteria.getClazz()!= null){
                return DBUtil.countData(criteria.getClazz(),criteria);
            }
        }
        return -1;
    }

    public static List<ImageEntity> getImg(Criteria criteria, long id) throws Exception {
        return DBUtil.execCustomSQL(criteria.getClazz(), CUSTOM_QUERY.sqlImg(CommonUtil.convertEntityToModel(criteria), id));
    }
    public static List<UploadEntity> getSourceUrl(Criteria criteria, long id) throws Exception{
        return DBUtil.execCustomSQL(criteria.getClazz(), CUSTOM_QUERY.sqlUpload(CommonUtil.convertEntityToModel(criteria), id));
    }
}
