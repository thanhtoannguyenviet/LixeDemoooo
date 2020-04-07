package Server.controller;

import Server.model.DAO.CategoryFilmDAO;
import Server.model.DB.CategoryfilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/Category")
@RestController
public class CategoryFilmController {
    @Autowired
    CategoryFilmDAO categoryFilmDAO;
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
    public  ResponseEntity<?> update (@RequestBody CategoryfilmEntity entity, @PathVariable Long id){
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
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> getAll(){

        return new ResponseEntity<>(categoryFilmDAO.getAll(),HttpStatus.OK);
    }
}