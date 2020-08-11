package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SerifilmFilmDAO;
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

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SerifilmFilmInDTO serifilmFilmInDTO) {
        if (serifilmFilmInDTO == null || serifilmFilmInDTO.getApiToken() == null
                || serifilmFilmInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(serifilmFilmInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SerifilmFilmEntity serifilmFilmEntity = serifilmFilmDAO.save(serifilmFilmInDTO.getSerifilmFilmEntity());
        return new ResponseEntity<>(serifilmFilmEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idSeri}/{idFilm}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idSeri") Long idSeri, @PathVariable("idFilm") Long idFilm) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
