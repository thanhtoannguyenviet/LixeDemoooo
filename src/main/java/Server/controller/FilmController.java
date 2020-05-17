package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.Criteria;
import Server.model.DTO.FilmDTO;
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
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmEntity entity){
        entity=filmDAO.save(entity);

        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody FilmEntity entity, @PathVariable("id") Long id){
        if(filmDAO.getByID(id)!=null)
        {
            filmDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(filmDAO.getByID(id)!=null){
            filmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> getDetail (@PathVariable("id") Long id){

        return new ResponseEntity<>(filmSiteDAO.getFilmDTOById(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetTop{item}" , method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@PathVariable int item)
    {
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setTop(item);
            List<FilmEntity> lsFilm = filmDAO.GetTop10(criteria);
            List<FilmDTO> lsFilmDTO = new ArrayList<>();
            for ( FilmEntity filmE : lsFilm
                 ) {
                lsFilmDTO.add(filmSiteDAO.getFilmDTOById(filmE.getId()));
            }
            return new ResponseEntity<>(lsFilmDTO,HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page,@PathVariable("itemOnPage") int itemOnPage){
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<FilmEntity> lsFilm = filmDAO.loadDataPagination(criteria);
            List<FilmDTO> lsFilmDTO = new ArrayList<>();
            for ( FilmEntity filmE : lsFilm)
            {
                lsFilmDTO.add(filmSiteDAO.getFilmDTOById(filmE.getId()));
            }
            return new ResponseEntity<>(lsFilmDTO,HttpStatus.OK);
       }catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(filmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }

}
