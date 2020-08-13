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

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmPostDTO filmEntity) {
        if (filmEntity == null || filmEntity.getApiToken() == null
                || filmEntity.getApiToken().isEmpty() || apiAccountDAO.checkToken(filmEntity.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
    public ResponseEntity<?> update(@RequestBody FilmInDTO filmInDTO, @PathVariable("id") Long id) {
        if (filmInDTO == null || filmInDTO.getApiToken() == null
                || filmInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(filmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (filmDAO.getByID(id) != null) {
            filmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmDTO filmDTO =filmSiteDAO.getFilmDTOById(id);
        FilmEntity filmEntity = filmDTO.getFilmEntity();
        filmEntity.setIndex(filmEntity.getIndex()+1);
        return new ResponseEntity<>(filmDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/GetTop{item}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable int item) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
    public ResponseEntity<?> getPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
    public ResponseEntity<?> getRandom(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("item") int item) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
}
