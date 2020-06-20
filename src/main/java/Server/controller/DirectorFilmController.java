package Server.controller;

import Server.model.DAO.DirectorFilmDAO;
import Server.model.DB.DirectorFilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api/FilmSite/DirectorFilm")
@RestController
public class DirectorFilmController {
    DirectorFilmDAO directorFilmDAO = new DirectorFilmDAO();
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody DirectorFilmEntity directorFilmEntity){
        directorFilmDAO.save(directorFilmEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(directorFilmDAO.getByID(id)!=null){
            directorFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
