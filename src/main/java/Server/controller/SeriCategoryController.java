package Server.controller;

import Server.model.DAO.SeriCategoryFilmDAO;
import Server.model.DB.SeriCategoryfilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("api/FilmSite/SeriCategory")
@RestController
public class SeriCategoryController {
    @Autowired
    SeriCategoryFilmDAO seriCategoryFilmDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SeriCategoryfilmEntity entity){
        seriCategoryFilmDAO.Save(entity);
       return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody SeriCategoryfilmEntity entity, @PathVariable Long id){
        if(seriCategoryFilmDAO.GetByID(id)!=null)
        {
            seriCategoryFilmDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(seriCategoryFilmDAO.GetByID(id)!=null){
            seriCategoryFilmDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
