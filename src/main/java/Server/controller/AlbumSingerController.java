package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AlbumSingerDAO;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.model.DB.AlbumSingerEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.AlbumSingerInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/AlbumSinger")
@RestController
public class AlbumSingerController {
    AlbumSingerDAO albumSingerDAO = new AlbumSingerDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumSingerInDTO albumSingerInDTO) {
        if (albumSingerInDTO == null || albumSingerInDTO.getApiToken() == null || albumSingerInDTO.getApiToken().isEmpty()
                || apiAccountDAO.checkToken(albumSingerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        AlbumSingerEntity albumSingerEntity = albumSingerDAO.save(albumSingerInDTO.getAlbumSingerEntity());
        return new ResponseEntity<>(albumSingerEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAlbum}/{idSinger}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idAlbum") Long idAlbum, @PathVariable("idSinger") Long idSinger) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (albumSingerDAO.getId(idAlbum, idSinger) != null) {
            for (AlbumSingerEntity item : albumSingerDAO.getId(idAlbum, idSinger)) {
                Long id = item.getId();
                albumSingerDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
