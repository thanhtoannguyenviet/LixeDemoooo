package Server.controller;

import Server.model.DAO.FilmActorDAO;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DB.FilmActorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/FilmActor")
@RestController
public class FilmActorController {
    FilmActorDAO filmActorDAO = new FilmActorDAO();
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody FilmActorEntity directorFilmEntity){
        filmActorDAO.save(directorFilmEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(filmActorDAO.getByID(id)!=null){
            filmActorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
