package Server.controller;

import Server.model.DAO.CategorySongDAO;
import Server.model.DB.CategorySongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RequestMapping("api/MusicSite/Category")
@RestController
public class CategorySongController {
    @Autowired
    CategorySongDAO categorySongDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CategorySongEntity categorySongEntity){
        categorySongDAO.Save(categorySongEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateCategorySong (@RequestBody CategorySongEntity singer, @PathVariable Long id){
        if(categorySongDAO.GetByID(id)!=null)
        {
            categorySongDAO.Save(singer);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(categorySongDAO.GetByID(id)!=null){
            categorySongDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }

}