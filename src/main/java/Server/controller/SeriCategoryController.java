package Server.controller;

import Server.model.DAO.SeriCategoryFilmDAO;
import Server.model.DB.SeriCategoryfilmEntity;
import Server.model.DTO.SeriCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/SeriCategory")
@RestController
public class SeriCategoryController {
    @Autowired
    SeriCategoryFilmDAO seriCategoryFilmDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SeriCategoryfilmEntity entity){
        seriCategoryFilmDAO.save(entity);
       return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody SeriCategoryfilmEntity entity, @PathVariable("id") Long id){
        if(seriCategoryFilmDAO.getByID(id)!=null)
        {
            seriCategoryFilmDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(seriCategoryFilmDAO.getByID(id)!=null){
            seriCategoryFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
