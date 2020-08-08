package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AlbumSingerDAO;
import Server.model.DAO.AlbumSongDAO;
import Server.model.DB.AlbumSingerEntity;
import Server.model.DB.AlbumSongEntity;
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

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumSongInDTO albumSongInDTO) {
        if (albumSongInDTO == null || albumSongInDTO.getApiToken() == null || albumSongInDTO.getApiToken().isEmpty()
                || apiAccountDAO.checkToken(albumSongInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        AlbumSongEntity albumSongEntity = albumSongDAO.save(albumSongInDTO.getAlbumSongEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAlbum}/{idSong}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody String apiToken, @PathVariable("idAlbum") Long idAlbum, @PathVariable("idSong") Long idSong) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
