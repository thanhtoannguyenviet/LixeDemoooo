package Server.controller;

import Server.model.DAO.LogDAO;
import Server.model.DAO.SearchDAO;
import Server.model.DB.CategorysongEntity;
import Server.model.DB.LogEntity;
import Server.model.DB.SearchEntity;
import Server.model.DTO.CategorySongDTO;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("Search")
@RestController
public class SearchController {
    @Autowired
    SearchDAO searchDAO;
    //{search}/model/{model}/
//    @RequestMapping(value = "/q/{search}/{model}",
//            method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    @ResponseBody
//    public ResponseEntity<?> getResultBasic(@PathVariable("search") String search, @PathVariable("model") String model) {
//        try {
//
//             return new ResponseEntity<>( searchDAO.getSearchBasic(search,model),HttpStatus.OK);
//               } catch (Exception e) {
//            LogEntity log = new LogEntity(e);
//            (new LogDAO()).save(log);
//            e.printStackTrace();
//            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
//        }
//    }
    @RequestMapping(value = "/q/{search}/{model}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getResultAdvance(@PathVariable("search") String search, @PathVariable("model") String model) {
        try {
            List<SearchEntity> ls = searchDAO.getSearch(search, model);
            if(ls!=null && !ls.isEmpty()){
                SearchEntity searchEntity = ls.get(0);
                return new ResponseEntity<>(searchEntity.getData(),HttpStatus.OK);
            }
//            searchEntity.setData(searchDAO.getSearchBasic(search,model).toString());
//            searchEntity.setKeyword(search);
//            searchEntity.setModel(model);
//            searchDAO.updateSearch(searchEntity);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/updateData/{search}/{model}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> updateSearch(@PathVariable("search") String search, @PathVariable("model") String model) {
        try {
                SearchEntity searchEntity = new SearchEntity();
                List<?> results =  searchDAO.getSearchBasic(search,model);
                String str ="";
            for( Object item :results ){

                    for (Field field : item.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if(field.getName().equals("id")){
                            Object value = field.get(item);
                            if (value != null) {
                                if(str==null||str.length()==0){
                                    str+=value;
                                }else
                                str+=","+value;
                            }
                        }
                    }
            }
                searchEntity.setData(str);
                searchEntity.setKeyword(search);
                searchEntity.setModel(model);
                searchEntity = searchDAO.Save(searchEntity);
                return new ResponseEntity<>(searchEntity,HttpStatus.OK);
            } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
}
