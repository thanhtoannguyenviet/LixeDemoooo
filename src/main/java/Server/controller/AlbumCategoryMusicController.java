package Server.controller;

import Server.model.DAO.AlbumCategoryMusicDAO;
import Server.model.DB.ActorEntity;
import Server.model.DB.AlbumCategorymusicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/AlbumCategory")
@RestController
public class AlbumCategoryMusicController {
    @Autowired
    AlbumCategoryMusicDAO albumCategoryMusicDAO;
    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody AlbumCategorymusicEntity albumCategorymusicEntity){
        albumCategoryMusicDAO.save(albumCategorymusicEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(albumCategoryMusicDAO.getByID(id)!=null){
            albumCategoryMusicDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
}
