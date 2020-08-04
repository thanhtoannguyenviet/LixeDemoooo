package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SerifilmFilmDAO;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DB.SerifilmFilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/SerifilmFilm")
@RestController
public class SerifilmFilmController {
    @Autowired
    SerifilmFilmDAO serifilmFilmDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SerifilmFilmEntity serifilmFilmEntity) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        serifilmFilmEntity=    serifilmFilmDAO.save(serifilmFilmEntity);
        return new ResponseEntity<>(serifilmFilmEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idSeri}/{idFilm}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("idSeri")  Long idSeri,@PathVariable("idFilm") Long idFilm) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        if (serifilmFilmDAO.getId(idSeri,idFilm) != null) {
            for (SerifilmFilmEntity item : serifilmFilmDAO.getId(idSeri,idFilm)) {
                Long id = item.getId();
                serifilmFilmDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
