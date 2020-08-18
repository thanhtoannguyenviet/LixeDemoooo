package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.FilmCategoryFilmDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.FilmCategoryfilmEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.FilmCategoryfilmInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("FilmSite/FilmCategory")
@RestController
public class FilmCategoryController {
    @Autowired
    FilmCategoryFilmDAO filmCategoryFilmDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody FilmCategoryfilmInDTO filmCategoryfilmInDTO, @RequestHeader String userToken) {
        if (filmCategoryfilmInDTO == null || apiAccountDAO.checkToken(filmCategoryfilmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(filmCategoryfilmInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        FilmCategoryfilmEntity entity = filmCategoryFilmDAO.save(filmCategoryfilmInDTO.getFilmCategoryfilmEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody FilmCategoryfilmInDTO filmCategoryfilmInDTO, @PathVariable("id") Long id,
                                    @RequestHeader String userToken) {
        if (filmCategoryfilmInDTO == null || apiAccountDAO.checkToken(filmCategoryfilmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(filmCategoryfilmInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (filmCategoryFilmDAO.getByID(id) != null) {
            filmCategoryFilmDAO.save(filmCategoryfilmInDTO.getFilmCategoryfilmEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{idFilm}/{idCategory}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idFilm") Long idFilm,
                                    @PathVariable("idCategory") Long idCategory, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (filmCategoryFilmDAO.getId(idFilm, idCategory) != null) {
            for (FilmCategoryfilmEntity item : filmCategoryFilmDAO.getId(idFilm, idCategory)) {
                Long id = item.getId();
                filmCategoryFilmDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

}
