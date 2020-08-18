package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SongCategorySongDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.SerifilmFilmEntity;
import Server.model.DB.SongCategorysongEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.SongCategorysongInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/SongCategorysong")
@RestController
public class SongCategorySongController {
    @Autowired
    SongCategorySongDAO songCategorySongDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    UserDAO userDAO = new UserDAO();


    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SongCategorysongInDTO songCategorysongInDTO, @RequestHeader String userToken) {
        if (songCategorysongInDTO == null || apiAccountDAO.checkToken(songCategorysongInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(songCategorysongInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        SongCategorysongEntity songCategorysongEntity = songCategorySongDAO.save(songCategorysongInDTO.getSongCategorysongEntity());
        return new ResponseEntity<>(songCategorysongEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idSong}/{idCategory}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idSong") Long idSong,
                                         @PathVariable("idCategory") Long idCategory, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (songCategorySongDAO.getId(idSong, idCategory) != null) {
            for (SongCategorysongEntity item : songCategorySongDAO.getId(idSong, idCategory)) {
                Long id = item.getId();
                songCategorySongDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
