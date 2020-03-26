package Server.controller;

import Server.model.DAO.AuthorDAO;
import Server.model.DB.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Author")
@RestController
public class AuthorController {
    @Autowired
    AuthorDAO authorDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AuthorEntity entity){

        entity =authorDAO.Save(entity);
        return new ResponseEntity<>(entity.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(authorDAO.GetByID(id)!=null){
            authorDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetAll",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> GetAll(){
        return new ResponseEntity<>(authorDAO.getAll(),HttpStatus.OK);
    }
}
