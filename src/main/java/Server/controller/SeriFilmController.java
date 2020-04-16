package Server.controller;

import Server.model.DAO.LogDAO;
import Server.model.DAO.SeriFilmDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.DirectorEntity;
import Server.model.DB.LogEntity;
import Server.model.DB.SerifilmEntity;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/Serifilm")
@RestController
public class SeriFilmController {
    @Autowired
     SeriFilmDAO seriFilmDAO;
     SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SerifilmEntity entity ){
        seriFilmDAO.Save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateAccount(@RequestBody SerifilmEntity entity, @PathVariable("id") Long id){
        if(seriFilmDAO.GetByID(id)!=null)
        {
            seriFilmDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        if(seriFilmDAO.GetByID(id)!=null){
            seriFilmDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id){
       return  new ResponseEntity<>(seriFilmDAO.GetByID(id),HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetTop10" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTop10() {
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(SerifilmEntity.class);
            criteria.setTop(10);
            return new ResponseEntity<>(seriFilmDAO.GetTop10(criteria), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(SerifilmEntity.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(seriFilmDAO.loadDataPagination(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(seriFilmDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().Save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
