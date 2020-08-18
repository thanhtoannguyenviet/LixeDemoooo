package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SerifilmFilmDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DB.SerifilmFilmEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.SerifilmFilmInDTO;
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
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SerifilmFilmInDTO serifilmFilmInDTO, @RequestHeader String userToken) {
        if (serifilmFilmInDTO == null || apiAccountDAO.checkToken(serifilmFilmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(serifilmFilmInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        SerifilmFilmEntity serifilmFilmEntity = serifilmFilmDAO.save(serifilmFilmInDTO.getSerifilmFilmEntity());
        return new ResponseEntity<>(serifilmFilmEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idSeri}/{idFilm}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idSeri") Long idSeri,
                                         @PathVariable("idFilm") Long idFilm, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (serifilmFilmDAO.getId(idSeri, idFilm) != null) {
            for (SerifilmFilmEntity item : serifilmFilmDAO.getId(idSeri, idFilm)) {
                Long id = item.getId();
                serifilmFilmDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
