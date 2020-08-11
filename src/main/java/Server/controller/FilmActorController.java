package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.FilmActorDAO;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DB.FilmActorEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.FilmActorInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/FilmSite/FilmActor")
@RestController
public class FilmActorController {
    FilmActorDAO filmActorDAO = new FilmActorDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody FilmActorInDTO filmActorInDTO) {
        if (filmActorInDTO == null || filmActorInDTO.getApiToken() == null
                || filmActorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(filmActorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmActorEntity filmActorEntity = filmActorDAO.save(filmActorInDTO.getFilmActorEntity());
        return new ResponseEntity<>(filmActorEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idFilm}/{idActor}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idFilm") Long idFilm, @PathVariable("idActor") Long idActor) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (filmActorDAO.getId(idFilm, idActor) != null) {
            for (FilmActorEntity item : filmActorDAO.getId(idFilm, idActor)) {
                Long id = item.getId();
                filmActorDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }
}
