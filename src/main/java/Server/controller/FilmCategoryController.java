package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.FilmCategoryFilmDAO;
import Server.model.DB.FilmCategoryfilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable("apiToken") String apiToken, @RequestBody FilmCategoryfilmEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        filmCategoryFilmDAO.save(entity);
        HttpHeaders responseHeader = new HttpHeaders();
        URI newAccounUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post completed", responseHeader, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable("apiToken") String apiToken, @RequestBody FilmCategoryfilmEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (filmCategoryFilmDAO.getByID(id) != null) {
            filmCategoryFilmDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (filmCategoryFilmDAO.getByID(id) != null) {
            filmCategoryFilmDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

}
