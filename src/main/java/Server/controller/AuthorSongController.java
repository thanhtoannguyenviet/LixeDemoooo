package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.AuthorSongDAO;
import Server.model.DB.AuthorSongEntity;
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

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AuthorSongInDTO authorSongInDTO) {
        if (authorSongInDTO == null || authorSongInDTO.getApiToken() == null || authorSongInDTO.getApiToken().isEmpty()
                || apiAccountDAO.checkToken(authorSongInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        AuthorSongEntity authorSongEntity = authorSongDAO.save(authorSongInDTO.getAuthorSongEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{idAuthor}/{idSong}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody String apiToken, @PathVariable("idAuthor") Long idAuthor, @PathVariable("idSong") Long idSong) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
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
