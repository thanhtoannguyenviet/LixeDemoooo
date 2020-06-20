package Server.controller;

import Server.model.DAO.AlbumSingerDAO;
import Server.model.DB.AlbumSingerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("api/MusicSite/AlbumSong")
@RestController
public class AlbumSongController {
    AlbumSingerDAO albumSingerDAO = new AlbumSingerDAO();
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumSingerEntity albumSingerEntity){
        albumSingerDAO.save(albumSingerEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(albumSingerDAO.getByID(id)!=null){
            albumSingerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
