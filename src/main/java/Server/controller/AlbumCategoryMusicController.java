package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AlbumCategoryMusicDAO;
import Server.model.DB.ActorEntity;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.model.DTO.AlbumCategorymusicInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/AlbumCategory")
@RestController
public class AlbumCategoryMusicController {
    @Autowired
    AlbumCategoryMusicDAO albumCategoryMusicDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumCategorymusicInDTO albumCategorymusicInDTO) {
        if (albumCategorymusicInDTO == null || albumCategorymusicInDTO.getApiToken() == null
                || albumCategorymusicInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(albumCategorymusicInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        AlbumCategorymusicEntity albumCategorymusicEntity = albumCategoryMusicDAO.save(albumCategorymusicInDTO.getAlbumCategorymusicEntity());
        return new ResponseEntity<>(albumCategorymusicEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAlbum}/{idCategory}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody String apiToken, @PathVariable("idAlbum") Long idAlbum, @PathVariable("idCategory") Long idCategory) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (albumCategoryMusicDAO.getId(idAlbum, idCategory) != null) {
            for (AlbumCategorymusicEntity item : albumCategoryMusicDAO.getId(idAlbum, idCategory)) {
                Long id = item.getId();
                albumCategoryMusicDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

}
