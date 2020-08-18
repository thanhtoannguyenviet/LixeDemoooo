package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.DirectorFilmDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.AlbumSongEntity;
import Server.model.DB.ApiaccountEntity;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.DirectorFilmInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/DirectorFilm")
@RestController
public class DirectorFilmController {

    // Not Use
    DirectorFilmDAO directorFilmDAO = new DirectorFilmDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody DirectorFilmInDTO directorFilmInDTO, @RequestHeader String userToken) {
        if (directorFilmInDTO == null || apiAccountDAO.checkToken(directorFilmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(directorFilmInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        DirectorFilmEntity directorFilmEntity = directorFilmDAO.save(directorFilmInDTO.getDirectorFilmEntity());
        return new ResponseEntity<>(directorFilmEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idDirector}/{idFilm}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idDirector") Long idDirector,
                                         @PathVariable("idFilm") Long idFilm, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (directorFilmDAO.getId(idDirector, idFilm) != null) {
            for (DirectorFilmEntity item : directorFilmDAO.getId(idDirector, idFilm)) {
                Long id = item.getId();
                directorFilmDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
