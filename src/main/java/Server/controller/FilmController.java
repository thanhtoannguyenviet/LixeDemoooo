package Server.controller;

import Server.model.DAO.FilmActorDAO;
import Server.model.DAO.FilmDAO;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api/FilmSite/Film")
@RestController
public class FilmController {
    @Autowired
    FilmDAO filmDAO;
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
    public  ResponseEntity<?> update (@RequestBody FilmEntity entity, @PathVariable Long id){
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
}
