package Server.controller;

import Server.model.DAO.DirectorDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.CategorysongEntity;
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
        directorDAO.Save(entity);
        HttpHeaders responseHeader=new HttpHeaders();
        URI newAccounUrl= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post completed",responseHeader, HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody DirectorEntity entity, @PathVariable("id") Long id){
        if(directorDAO.GetByID(id)!=null)
        {
            directorDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(directorDAO.GetByID(id)!=null){
            directorDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> get (@PathVariable("id") Long id){
     return  new ResponseEntity<>(directorDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetTop10/" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getTop10()
    {
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(DirectorEntity.class);
        criteria.setTop(10);
        return new ResponseEntity<>(signalDAO.findData(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(DirectorEntity.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(signalDAO.findData(criteria),HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
