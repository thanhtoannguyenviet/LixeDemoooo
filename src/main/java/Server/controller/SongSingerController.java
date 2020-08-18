package Server.controller;


import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SongSingerDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.SongCategorysongEntity;
import Server.model.DB.SongSingerEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.SongSingerInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/SongSinger")
@RestController
public class SongSingerController {
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SongSingerInDTO songSingerInDTO, @RequestHeader String userToken) {
        if (songSingerInDTO == null || apiAccountDAO.checkToken(songSingerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(songSingerInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongSingerEntity songSingerEntity = songSingerDAO.save(songSingerInDTO.getSongSingerEntity());
        return new ResponseEntity<>(songSingerEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idSong}/{idSinger}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idSong") Long idSong,
                                         @PathVariable("idSinger") Long idSinger, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (songSingerDAO.getId(idSong, idSinger) != null) {
            for (SongSingerEntity item : songSingerDAO.getId(idSong, idSinger)) {
                Long id = item.getId();
                songSingerDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
