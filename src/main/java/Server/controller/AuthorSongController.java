package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AuthorSongDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.AuthorSongEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.AuthorSongInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Author")
@RestController
public class AuthorSongController {

    AuthorSongDAO authorSongDAO = new AuthorSongDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AuthorSongInDTO authorSongInDTO, @RequestHeader String userToken) {
        if (authorSongInDTO == null || apiAccountDAO.checkToken(authorSongInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(authorSongInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AuthorSongEntity authorSongEntity = authorSongDAO.save(authorSongInDTO.getAuthorSongEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAuthor}/{idSong}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("idAuthor") Long idAuthor,
                                         @PathVariable("idSong") Long idSong, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (authorSongDAO.getId(idAuthor, idSong) != null) {
            for (AuthorSongEntity item : authorSongDAO.getId(idAuthor, idSong)) {
                Long id = item.getId();
                authorSongDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
