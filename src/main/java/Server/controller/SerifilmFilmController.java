package Server.controller;

import Server.model.DAO.SerifilmFilmDAO;
import Server.model.DB.SerifilmFilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/SerifilmFilm")
@RestController
public class SerifilmFilmController {
    @Autowired
    SerifilmFilmDAO serifilmFilmDAO;
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SerifilmFilmEntity serifilmFilmEntity){
        serifilmFilmDAO.save(serifilmFilmEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(serifilmFilmDAO.getByID(id)!=null){
            serifilmFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
