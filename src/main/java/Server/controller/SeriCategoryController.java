package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.CategoryfilmEntity;
import Server.model.DB.LogEntity;
import Server.model.DB.SeriCategoryfilmEntity;
import Server.model.DTO.CategoryFilmDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SeriCategoryDTO;
import Server.model.DTO.SeriFilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/FilmSite/SeriCategory")
@RestController
public class SeriCategoryController {
    @Autowired
    SeriCategoryFilmDAO seriCategoryFilmDAO;
    CategoryFilmDAO categoryFilmDAO = new CategoryFilmDAO();
    FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable("apiToken") String apiToken, @RequestBody SeriCategoryfilmEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        seriCategoryFilmDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable("apiToken") String apiToken, @RequestBody SeriCategoryfilmEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (seriCategoryFilmDAO.getByID(id) != null) {
            seriCategoryFilmDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (seriCategoryFilmDAO.getByID(id) != null) {
            seriCategoryFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/GetAll/",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> getAll(@PathVariable("apiToken") String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            List<CategoryfilmEntity> categoryfilmEntityList = categoryFilmDAO.getAll();
            if (!categoryfilmEntityList.isEmpty()) {
                List<SeriCategoryDTO> seriCategoryDTOList = new ArrayList<>();
                for (CategoryfilmEntity item : categoryfilmEntityList
                ) {
                    SeriCategoryDTO categoryFilmDTO = getSeriCategoryDTO(item);
                    if (categoryFilmDTO != null)
                        seriCategoryDTOList.add(categoryFilmDTO);
                }
                return new ResponseEntity<>(seriCategoryDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getPage(@PathVariable("apiToken") String apiToken, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(CategoryfilmEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<CategoryfilmEntity> categoryfilmEntityList = categoryFilmDAO.loadDataPagination(criteria);
            if (!categoryfilmEntityList.isEmpty()) {
                List<SeriCategoryDTO> seriCategoryDTOList = new ArrayList<>();
                for (CategoryfilmEntity item : categoryfilmEntityList
                ) {
                    SeriCategoryDTO categoryFilmDTO = getSeriCategoryDTO(item);
                    if (categoryFilmDTO != null)
                        seriCategoryDTOList.add(categoryFilmDTO);
                }
                return new ResponseEntity<>(seriCategoryDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetTop{itop}/", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop(@PathVariable("apiToken") String apiToken, @PathVariable("itop") int itop) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(CategoryfilmEntity.class);
            criteria.setTop(itop);
            List<CategoryfilmEntity> categoryfilmEntityList = categoryFilmDAO.getTop10(criteria);
            if (!categoryfilmEntityList.isEmpty()) {
                List<SeriCategoryDTO> seriCategoryDTOList = new ArrayList<>();
                for (CategoryfilmEntity item : categoryfilmEntityList
                ) {
                    SeriCategoryDTO categoryFilmDTO = getSeriCategoryDTO(item);
                    if (categoryFilmDTO != null)
                        seriCategoryDTOList.add(categoryFilmDTO);
                }
                return new ResponseEntity<>(seriCategoryDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetRandom{iRandom}/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> getRandom(@PathVariable("apiToken") String apiToken, @PathVariable("iRandom") int iRandom) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setTop(iRandom);
            criteria.setClazz(CategoryfilmEntity.class);
            List<CategoryfilmEntity> categoryfilmEntityList = categoryFilmDAO.getTopRandom(criteria);
            if (!categoryfilmEntityList.isEmpty()) {
                List<SeriCategoryDTO> seriCategoryDTOList = new ArrayList<>();
                for (CategoryfilmEntity item : categoryfilmEntityList
                ) {
                    SeriCategoryDTO categoryFilmDTO = getSeriCategoryDTO(item);
                    if (categoryFilmDTO != null)
                        seriCategoryDTOList.add(categoryFilmDTO);
                }
                return new ResponseEntity<>(seriCategoryDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/Count", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@PathVariable("apiToken") String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(categoryFilmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private SeriCategoryDTO getSeriCategoryDTO(CategoryfilmEntity categoryfilmEntity) {
        List<SeriCategoryfilmEntity> seriCategoryfilmEntity = seriCategoryFilmDAO.getId("categoryid", categoryfilmEntity.getId() + "");
        List<SeriFilmDTO> seriFilmDTOList = new ArrayList<>();
        for (SeriCategoryfilmEntity item : seriCategoryfilmEntity) {
            SeriFilmDTO seriFilmDTO = filmSiteDAO.getSeriFilmDTO(item.getSeriid());
            if (seriFilmDTO != null)
                seriFilmDTOList.add(seriFilmDTO);
        }
        return new SeriCategoryDTO(categoryfilmEntity, seriFilmDTOList);
    }
}
