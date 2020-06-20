package Server.controller;

import Server.model.DAO.SongCategorySongDAO;
import Server.model.DB.SongCategorysongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/SongCategorysong")
@RestController
public class SongCategorySongController {
    @Autowired
    SongCategorySongDAO songCategorySongDAO;
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody SongCategorysongEntity serifilmFilmEntity){
        songCategorySongDAO.save(serifilmFilmEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(songCategorySongDAO.getByID(id)!=null){
            songCategorySongDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
