package Server.controller;

import Server.model.DAO.FilmActorDAO;
import Server.model.DAO.FilmCategoryFilmDAO;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmCategoryFilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("Relationship/FilmCategory/")
@RestController
public class FilmCategoryController {
    @Autowired
    FilmCategoryFilmDAO filmCategoryFilmDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmCategoryFilmEntity entity){
        filmCategoryFilmDAO.Save(entity);
        HttpHeaders responseHeader=new HttpHeaders();
        URI newAccounUrl= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post completed",responseHeader, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody FilmCategoryFilmEntity entity, @PathVariable Long id){
        if(filmCategoryFilmDAO.GetByID(id)!=null)
        {
            filmCategoryFilmDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(filmCategoryFilmDAO.GetByID(id)!=null){
            filmCategoryFilmDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
