package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.DirectorFilmDAO;
import Server.model.DB.AlbumSongEntity;
import Server.model.DB.ApiaccountEntity;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DTO.DirectorFilmInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/DirectorFilm")
@RestController
public class DirectorFilmController {
    DirectorFilmDAO directorFilmDAO = new DirectorFilmDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody DirectorFilmInDTO directorFilmInDTO) {
        if (directorFilmInDTO == null || directorFilmInDTO.getApiToken() == null
                || directorFilmInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(directorFilmInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        DirectorFilmEntity directorFilmEntity =   directorFilmDAO.save(directorFilmInDTO.getDirectorFilmEntity());
        return new ResponseEntity<>(directorFilmEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idDirector}/{idFilm}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody String apiToken, @PathVariable("idDirector")  Long idDirector,@PathVariable("idFilm")  Long idFilm) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (directorFilmDAO.getId(idDirector,idFilm) != null) {
            for (DirectorFilmEntity item : directorFilmDAO.getId(idDirector,idFilm)) {
                Long id = item.getId();
                directorFilmDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
