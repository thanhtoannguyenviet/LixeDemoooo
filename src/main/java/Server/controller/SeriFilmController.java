package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.LogEntity;
import Server.model.DB.SerifilmEntity;
import Server.model.DB.SerifilmFilmEntity;
import Server.model.DTO.Criteria;
import Server.model.DTO.FilmDTO;
import Server.model.DTO.SeriFilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("api/FilmSite/Serifilm")
@RestController
public class SeriFilmController {
    @Autowired
    SeriFilmDAO seriFilmDAO;
    FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
    SerifilmFilmDAO serifilmFilmDAO = new SerifilmFilmDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SerifilmEntity entity) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        entity = seriFilmDAO.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody SerifilmEntity entity, @PathVariable("id") Long id) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        if (seriFilmDAO.getByID(id) != null) {
            seriFilmDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        if (seriFilmDAO.getByID(id) != null) {
            seriFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        SerifilmEntity serifilmEntity = seriFilmDAO.getByID(id);
        if (serifilmEntity != null) {
            return new ResponseEntity<>(filmSiteDAO.getSeriFilmDTO(serifilmEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetTop{iTop}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@PathVariable("iTop") int iTop) {
        try {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SerifilmEntity.class);
            criteria.setTop(iTop);
            List<SerifilmEntity> serifilmEntityList = seriFilmDAO.getTop10(criteria);
            List<SeriFilmDTO> seriFilmDTOList = new ArrayList<>();
            if (!serifilmEntityList.isEmpty()) {
                for (SerifilmEntity item : serifilmEntityList) {
                    seriFilmDTOList.add(filmSiteDAO.getSeriFilmDTO(item));
                }
                return new ResponseEntity<>(seriFilmDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SerifilmEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<SerifilmEntity> serifilmEntityList = seriFilmDAO.loadDataPagination(criteria);
            List<SeriFilmDTO> seriFilmDTOList = new ArrayList<>();
            if (!seriFilmDTOList.isEmpty()) {
                for (SerifilmEntity item : serifilmEntityList) {
                    seriFilmDTOList.add(filmSiteDAO.getSeriFilmDTO(item));
                }
                return new ResponseEntity<>(seriFilmDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Count", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count() {
        try {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
            return new ResponseEntity<>(seriFilmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
}
