package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.SongCategorySongDAO;
import Server.model.DB.SerifilmFilmEntity;
import Server.model.DB.SongCategorysongEntity;
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
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SongCategorysongEntity songCategorysongEntity){
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        songCategorysongEntity=  songCategorySongDAO.save(songCategorysongEntity);
        return new ResponseEntity<>(songCategorysongEntity, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{idSong}/{idCategory}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("idSong") Long idSong,@PathVariable("idCategory") Long idCategory){
//            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
//                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//            }
        if (songCategorySongDAO.getId(idSong,idCategory) != null) {
            for (SongCategorysongEntity item : songCategorySongDAO.getId(idSong,idCategory)) {
                Long id = item.getId();
                songCategorySongDAO.delete(id);
            }
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
