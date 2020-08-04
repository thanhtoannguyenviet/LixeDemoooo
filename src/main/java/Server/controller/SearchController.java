package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.CategorySongDTO;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequestMapping("api/Search")
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
                String[] arrSearch =searchEntity.getData().split(",");
                Arrays.sort(arrSearch, Collections.reverseOrder());
                if(searchEntity.getModel().equals("film")){
                    FilmDAO filmDAO = new FilmDAO();
                    List<FilmEntity> lsFilm = new ArrayList<>();
                    for(String item : arrSearch){
                        lsFilm.add(filmDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsFilm,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("song")){
                    SongDAO songDAO = new SongDAO();
                    List<SongEntity> lsSong = new ArrayList<>();
                    for(String item : arrSearch){
                        lsSong.add(songDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsSong,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("actor")){
                    ActorDAO actorDAO = new ActorDAO();
                    List<ActorEntity> lsActor = new ArrayList<>();
                    for(String item : arrSearch){
                        lsActor.add(actorDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsActor,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("album")){
                    AlbumDAO albumDAO = new AlbumDAO();
                    List<AlbumEntity> lsAlbum = new ArrayList<>();
                    for(String item : arrSearch){
                        lsAlbum.add(albumDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsAlbum,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("author")){
                    AuthorDAO authorDAO = new AuthorDAO();
                    List<AuthorEntity> lsAuthor = new ArrayList<>();
                    for(String item : arrSearch){
                        lsAuthor.add(authorDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsAuthor,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("categoryfilm")){
                    CategoryFilmDAO authorDAO = new CategoryFilmDAO();
                    List<CategoryfilmEntity> lsCategoryfilm = new ArrayList<>();
                    for(String item : arrSearch){
                        lsCategoryfilm.add(authorDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsCategoryfilm,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("categorysong")){
                    CategorySongDAO categorySongDAO = new CategorySongDAO();
                    List<CategorysongEntity> lsCategorysong = new ArrayList<>();
                    for(String item : arrSearch){
                        lsCategorysong.add(categorySongDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsCategorysong,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("director")){
                    DirectorDAO directorDAO = new DirectorDAO();
                    List<DirectorEntity> lsDirector = new ArrayList<>();
                    for(String item : arrSearch){
                        lsDirector.add(directorDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsDirector,HttpStatus.OK);
                }
                if(searchEntity.getModel().equals("singer")){
                    SingerDAO singerDAO = new SingerDAO();
                    List<SingerEntity> lsSinger = new ArrayList<>();
                    for(String item : arrSearch){
                        lsSinger.add(singerDAO.getByID(Long.parseLong(item)));
                    }
                    return new ResponseEntity<>(lsSinger    ,HttpStatus.OK);
                }
            }
            else{
                List<?> results =  searchDAO.getSearchBasic(search,model);
                SearchEntity searchEntity = new SearchEntity();
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
                 searchDAO.Save(searchEntity);
                return new ResponseEntity<>(results,HttpStatus.OK);
            }
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
                List<SearchEntity> lsSearch = searchDAO.getSearch(search,model);
                SearchEntity searchEntity = new SearchEntity();
                if(!lsSearch.isEmpty()){
                    searchEntity=lsSearch.get(0);
                }
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
