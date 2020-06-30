package Server.controller;


import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SongSingerDAO;
import Server.model.DB.SongSingerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/SongSinger")
@RestController
public class SongSingerController {
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SongSingerEntity albumSingerEntity) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        songSingerDAO.save(albumSingerEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id) {
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        if (songSingerDAO.getByID(id) != null) {
            songSingerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
