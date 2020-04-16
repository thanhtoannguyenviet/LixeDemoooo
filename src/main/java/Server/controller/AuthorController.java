package Server.controller;

import Server.model.DAO.AuthorDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.AuthorEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Author")
@RestController
public class AuthorController {
    @Autowired
    AuthorDAO authorDAO;
    SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AuthorEntity entity){
        entity =authorDAO.Save(entity);
        return new ResponseEntity<>(entity.getId(), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(authorDAO.GetByID(id)!=null){
            authorDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetAll",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> GetAll(){
        return new ResponseEntity<>(authorDAO.getAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/Count",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> count (){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateActor (@RequestBody AuthorEntity actor, @PathVariable("id") Long id){
        if(id==actor.getId())
        {
            authorDAO.Save(actor);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getDetail (@PathVariable("id") Long id){
        return new ResponseEntity<>(authorDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page) {
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(AuthorEntity.class);
            criteria.setCurrentPage(page);
            return new ResponseEntity<>(signalDAO.findData(criteria), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetTop10/", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop (){
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(AuthorEntity.class);
            criteria.setTop(10);
            return new ResponseEntity<>(signalDAO.findData(criteria), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
