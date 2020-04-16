package Server.controller;

import Server.model.DAO.CategoryFilmDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.AuthorEntity;
import Server.model.DB.CategoryfilmEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/Category")
@RestController
public class CategoryFilmController {
    @Autowired
    CategoryFilmDAO categoryFilmDAO;
    SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody CategoryfilmEntity categorySongEntity){
        categoryFilmDAO.Save(categorySongEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody CategoryfilmEntity entity, @PathVariable("id") Long id){
        if(id==entity.getId())
        {
            categoryFilmDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(categoryFilmDAO.GetByID(id)!=null){
            categoryFilmDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("id") Long id){

        return new ResponseEntity<>(categoryFilmDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetAll/",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> getAll(){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(CategoryfilmEntity.class);
        return new ResponseEntity<>(SignalDAO.findData(criteria),HttpStatus.OK);
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
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(CategoryfilmEntity.class);
            criteria.setCurrentPage(page);
            return new ResponseEntity<>(signalDAO.findData(criteria), HttpStatus.OK);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetTop10/", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop (){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(CategoryfilmEntity.class);
        criteria.setTop(10);
        return new ResponseEntity<>(signalDAO.findData(criteria),HttpStatus.OK);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}
