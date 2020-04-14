package Server.common;

import Server.model.DB.FilmEntity;
import Server.model.DB.SongEntity;
import Server.model.DTO.Criteria;

public class CommonUtil {

    public static String convertEntityToModel(Criteria criteria) throws Exception {
        if(null == criteria.getClazz()){
            throw new Exception();
        }
        if(SongEntity.class.equals(criteria.getClazz())){
            return "song";
        }
        else if(FilmEntity.class.equals(criteria.getClazz())){
            return "film";
        }
        return null;
    }
}
