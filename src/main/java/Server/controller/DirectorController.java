package Server.controller;

import Server.model.DAO.DirectorDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.DirectorEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RequestMapping("api/FilmSite/Director")
@RestController
public class DirectorController {
    @Autowired
    DirectorDAO directorDAO;
    SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody DirectorEntity entity){
        directorDAO.save(entity);
        HttpHeaders responseHeader=new HttpHeaders();
        URI newAccounUrl= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post completed",responseHeader, HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody DirectorEntity entity, @PathVariable("id") Long id){
        if(directorDAO.getByID(id)!=null)
        {
            directorDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(directorDAO.getByID(id)!=null){
            directorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> get (@PathVariable("id") Long id){
     return  new ResponseEntity<>(directorDAO.getByID(id),HttpStatus.OK);
    }

    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(DirectorEntity.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(directorDAO.loadDataPagination(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(directorDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
