package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AlbumCategoryMusicDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.ActorEntity;
import Server.model.DB.AlbumCategorymusicEntity;
import Server.model.DTO.APIAccountDTO;
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
		UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumCategorymusicInDTO albumCategorymusicInDTO, @RequestHeader String userToken) {
        if (albumCategorymusicInDTO == null || apiAccountDAO.checkToken(albumCategorymusicInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(albumCategorymusicInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AlbumCategorymusicEntity albumCategorymusicEntity = albumCategoryMusicDAO.save(albumCategorymusicInDTO.getAlbumCategorymusicEntity());
        return new ResponseEntity<>(albumCategorymusicEntity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAlbum}/{idCategory}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idAlbum") Long idAlbum,
                                         @PathVariable("idCategory") Long idCategory, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (albumCategoryMusicDAO.getId(idAlbum, idCategory) != null) {
            for (AlbumCategorymusicEntity item : albumCategoryMusicDAO.getId(idAlbum, idCategory)) {
                Long id = item.getId();
                albumCategoryMusicDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

}
