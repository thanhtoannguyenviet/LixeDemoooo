package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
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
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

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
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getResultAdvance(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("search") String search,
                                              @PathVariable("model") String model, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            List<SearchEntity> ls = searchDAO.getSearch(search, model);
            if (ls != null && !ls.isEmpty()) {
                SearchEntity searchEntity = ls.get(0);
                String[] arrSearch = searchEntity.getData().split(",");
                Arrays.sort(arrSearch, Collections.reverseOrder());
                List<?> lsResult = getResult(searchEntity, arrSearch);
                return new ResponseEntity<>(lsResult, HttpStatus.OK);
            } else {
                List<?> results = searchDAO.getSearchBasic(search, model);
                SearchEntity searchEntity = new SearchEntity();
                String str = "";
                for (Object item : results) {

                    for (Field field : item.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        if (field.getName().equals("id")) {
                            Object value = field.get(item);
                            if (value != null) {
                                if (str == null || str.length() == 0) {
                                    str += value;
                                } else
                                    str += "," + value;
                            }
                        }
                    }
                }
                searchEntity.setData(str);
                searchEntity.setKeyword(search);
                searchEntity.setModel(model);
                searchDAO.Save(searchEntity);
                return new ResponseEntity<>(results, HttpStatus.OK);
            }
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateData/{search}/{model}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> updateSearch(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("search") String search,
                                          @PathVariable("model") String model, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            List<SearchEntity> lsSearch = searchDAO.getSearch(search, model);
            SearchEntity searchEntity = new SearchEntity();
            if (!lsSearch.isEmpty()) {
                searchEntity = lsSearch.get(0);
            }
            List<?> results = searchDAO.getSearchBasic(search, model);
            String str = "";
            for (Object item : results) {

                for (Field field : item.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.getName().equals("id")) {
                        Object value = field.get(item);
                        if (value != null) {
                            if (str == null || str.length() == 0) {
                                str += value;
                            } else
                                str += "," + value;
                        }
                    }
                }
            }
            searchEntity.setData(str);
            searchEntity.setKeyword(search);
            searchEntity.setModel(model);
            searchEntity = searchDAO.Save(searchEntity);
            String[] arrSearch = searchEntity.getData().split(",");
            List<?> lsResult = getResult(searchEntity, arrSearch);
            return new ResponseEntity<>(lsResult, HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private List<?> getResult(SearchEntity searchEntity, String[] arrSearch) {
        if (searchEntity.getModel().equals("film")) {
            FilmDAO filmDAO = new FilmDAO();
            List<FilmDTO> lsFilm = new ArrayList<>();
            for (String item : arrSearch) {
                if (!item.equals("") &&item!=null) {
                    FilmEntity filmEntity = filmDAO.getByID(Long.parseLong(item));
                    if (filmEntity != null) {
                        FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
                        lsFilm.add(filmSiteDAO.getFilmDTOById(filmEntity));
                    }
                }
            }
            return lsFilm;
        }
        SongSiteDAO songSiteDAO = new SongSiteDAO();
        if (searchEntity.getModel().equals("song")) {
            SongDAO songDAO = new SongDAO();
            List<SongDTO> lsSong = new ArrayList<>();
            for (String item : arrSearch) {
                if (!item.equals("")) {
                    SongEntity songEntity = songDAO.getByID(Long.parseLong(item));
                    if (songEntity != null) {
                        SongDTO songDTO = songSiteDAO.getSongDTOById(songEntity);
                        lsSong.add(songDTO);
                    }
                }
            }
            return lsSong;
        }
        if (searchEntity.getModel().equals("actor")) {
            ActorDAO actorDAO = new ActorDAO();
            List<ActorEntity> lsActor = new ArrayList<>();
            for (String item : arrSearch) {
                lsActor.add(actorDAO.getByID(Long.parseLong(item)));
            }
            return lsActor;
        }
        if (searchEntity.getModel().equals("album")) {
            AlbumDAO albumDAO = new AlbumDAO();
            List<AlbumEntity> lsAlbum = new ArrayList<>();
            for (String item : arrSearch) {
                lsAlbum.add(albumDAO.getByID(Long.parseLong(item)));
            }
            return lsAlbum;
        }
        if (searchEntity.getModel().equals("author")) {
            AuthorDAO authorDAO = new AuthorDAO();
            List<AuthorEntity> lsAuthor = new ArrayList<>();
            for (String item : arrSearch) {
                lsAuthor.add(authorDAO.getByID(Long.parseLong(item)));
            }
            return lsAuthor;
        }
        if (searchEntity.getModel().equals("categoryfilm")) {
            CategoryFilmDAO authorDAO = new CategoryFilmDAO();
            List<CategoryfilmEntity> lsCategoryfilm = new ArrayList<>();
            for (String item : arrSearch) {
                lsCategoryfilm.add(authorDAO.getByID(Long.parseLong(item)));
            }
            return lsCategoryfilm;
        }
        if (searchEntity.getModel().equals("categorysong")) {
            CategorySongDAO categorySongDAO = new CategorySongDAO();
            List<CategorysongEntity> lsCategorysong = new ArrayList<>();
            for (String item : arrSearch) {
                lsCategorysong.add(categorySongDAO.getByID(Long.parseLong(item)));
            }
            return lsCategorysong;
        }
        if (searchEntity.getModel().equals("director")) {
            DirectorDAO directorDAO = new DirectorDAO();
            List<DirectorEntity> lsDirector = new ArrayList<>();
            for (String item : arrSearch) {
                lsDirector.add(directorDAO.getByID(Long.parseLong(item)));
            }
            return lsDirector;
        }
        if (searchEntity.getModel().equals("singer")) {
            SingerDAO singerDAO = new SingerDAO();
            List<SingerDTO> lsSinger = new ArrayList<>();
            for (String item : arrSearch) {
                if (!item.equals("")) {
                    SingerEntity singerEntity = singerDAO.getByID(Long.parseLong(item));
                    if (singerEntity != null) {
                        SingerDTO singerDTO = songSiteDAO.getSingerDTO(singerEntity);
                        if (singerEntity != null) {
                            lsSinger.add(singerDTO);
                        }
                    }
                }
            }
            return lsSinger;
        }
        return null;
    }
}
