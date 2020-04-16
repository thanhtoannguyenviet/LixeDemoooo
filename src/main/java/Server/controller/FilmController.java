package Server.controller;

import Server.model.DAO.FilmActorDAO;
import Server.model.DAO.FilmDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.DirectorEntity;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api/FilmSite/Film")
@RestController
public class FilmController {
    @Autowired
    FilmDAO filmDAO;
    SignalDAO signalDAO;
    @Autowired
    FilmActorDAO filmActorDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmEntity entity){
        entity=filmDAO.Save(entity);
        FilmActorEntity filmActorEntity = new FilmActorEntity();
        filmActorEntity.setActorid(entity.getActorid());
        filmActorEntity.setFilmid(entity.getId());
        filmActorDAO.Save(filmActorEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody FilmEntity entity, @PathVariable("id") Long id){
        if(filmDAO.GetByID(id)!=null)
        {
            filmDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(filmDAO.GetByID(id)!=null){
            filmDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getDetail (@PathVariable("id") Long id){
        return new ResponseEntity<>(filmDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetTop10" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTop10()
    {
        try{
            Criteria criteria = new Criteria();
            criteria.setClazz(FilmEntity.class);
            criteria.setTop(10);
            return new ResponseEntity<>(filmDAO.GetTop10(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(FilmEntity.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(filmDAO.loadDataPagination(criteria),HttpStatus.OK);
        }catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(filmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().Save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
