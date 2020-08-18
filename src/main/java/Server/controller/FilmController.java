package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("api/FilmSite/Film")
@RestController
public class FilmController {
    @Autowired
    FilmDAO filmDAO;
    @Autowired
    FilmSiteDAO filmSiteDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmPostDTO filmEntity, @RequestHeader String userToken) {
        if (filmEntity == null || apiAccountDAO.checkToken(filmEntity.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(filmEntity.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SearchDAO searchDAO = new SearchDAO();
        FilmEntity entity = filmDAO.save(filmEntity.getFilmEntity());
        String strKey[] = filmEntity.getKeyword().split(",");
        for (String item : strKey) {
            List<SearchEntity> searchLs = searchDAO.getSearch(item, "film");
            if (!searchLs.isEmpty()) {
                SearchEntity searchEntity = searchLs.get(0);
                String mergeStr = searchEntity.getData() + "," + entity.getId();
                searchEntity.setData(mergeStr);
                searchDAO.Save(searchEntity);
            } else {
                SearchEntity searchEntity = new SearchEntity();
                searchEntity.setKeyword(item);
                searchEntity.setData(entity.getId() + "");
                searchEntity.setModel("film");
                searchDAO.Save(searchEntity);
            }
        }
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody FilmInDTO filmInDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (filmInDTO == null || apiAccountDAO.checkToken(filmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(filmInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (filmDAO.getByID(id) != null) {
            filmDAO.save(filmInDTO.getFilmEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (filmDAO.getByID(id) != null) {
            filmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,
                                       @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        FilmDTO filmDTO = filmSiteDAO.getFilmDTOById(id);
        FilmEntity filmEntity = filmDTO.getFilmEntity();
        filmEntity.setIndex(filmEntity.getIndex() + 1);
        return new ResponseEntity<>(filmDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/GetTop{item}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable int item, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setTop(item);
            List<FilmEntity> lsFilm = filmDAO.GetTop10(criteria);
            List<FilmDTO> lsFilmDTO = new ArrayList<>();
            for (FilmEntity filmE : lsFilm
            ) {
                lsFilmDTO.add(filmSiteDAO.getFilmDTOById(filmE.getId()));
            }
            return new ResponseEntity<>(lsFilmDTO, HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page,
                                     @PathVariable("itemOnPage") int itemOnPage, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<FilmEntity> lsFilm = filmDAO.loadDataPagination(criteria);
            List<FilmDTO> lsFilmDTO = new ArrayList<>();
            for (FilmEntity filmE : lsFilm) {
                lsFilmDTO.add(filmSiteDAO.getFilmDTOById(filmE.getId()));
            }
            return new ResponseEntity<>(lsFilmDTO, HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Count", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(filmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetRandom{item}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getRandom(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("item") int item, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setTop(item);
            List<FilmEntity> filmEntityList = filmDAO.loadTopRandom(criteria);
            List<FilmDTO> lsFilmDTO = new ArrayList<>();
            for (FilmEntity filmE : filmEntityList) {
                lsFilmDTO.add(filmSiteDAO.getFilmDTOById(filmE.getId()));
            }
            if (!filmEntityList.isEmpty())
                return new ResponseEntity<>(lsFilmDTO, HttpStatus.OK);
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/UpdateRange",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop(@RequestBody APIAccountDTO apiAccountDTO) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            List<FilmEntity> filmEntityList = filmDAO.getWithIndex("film");
            if(filmEntityList!=null){
                for(int i=0;i<filmEntityList.size();i++){
                    FilmEntity filmEntity =  filmEntityList.get(i);
                    filmEntity.setRange(i+1);
                    filmDAO.save(filmEntity);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
}
