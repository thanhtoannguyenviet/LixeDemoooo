package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AlbumSingerDAO;
import Server.model.DAO.AlbumSongDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.AlbumSingerEntity;
import Server.model.DB.AlbumSongEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.AlbumSongInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/AlbumSong")
@RestController
public class AlbumSongController {
    AlbumSongDAO albumSongDAO = new AlbumSongDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumSongInDTO albumSongInDTO, @RequestHeader String userToken) {
        if (albumSongInDTO == null || apiAccountDAO.checkToken(albumSongInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(albumSongInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        AlbumSongEntity albumSongEntity = albumSongDAO.save(albumSongInDTO.getAlbumSongEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAlbum}/{idSong}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idAlbum") Long idAlbum,
                                         @PathVariable("idSong") Long idSong, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (albumSongDAO.getId(idAlbum, idSong) != null) {
            for (AlbumSongEntity item : albumSongDAO.getId(idAlbum, idSong)) {
                Long id = item.getId();
                albumSongDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
