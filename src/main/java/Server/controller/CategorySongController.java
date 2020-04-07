package Server.controller;

import Server.model.DAO.CategorySongDAO;
import Server.model.DB.CategorysongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Category")
@RestController
public class CategorySongController {
    @Autowired
    CategorySongDAO categorySongDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CategorysongEntity categorySongEntity){
        categorySongDAO.Save(categorySongEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateCategorySong (@RequestBody CategorysongEntity singer, @PathVariable Long id){
        if(id==singer.getId())
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
    @RequestMapping(value = "/GetDetail/{id}" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable Long id)
    {
        return new ResponseEntity<>(categorySongDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/GetAll/" , method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAll(@PathVariable Long id)
    {
        return new ResponseEntity<>(categorySongDAO.getAll(),HttpStatus.OK);
    }
}
