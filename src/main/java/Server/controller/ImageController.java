package Server.controller;

import Server.model.DAO.ImageDAO;
import Server.model.DB.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("Image")
@RestController
public class ImageController {
    @Autowired
    ImageDAO imageDAO;

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody ImageEntity entity){
        imageDAO.save(entity);
        return new ResponseEntity<>("Post Completed",HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update(@RequestBody ImageEntity entity, @PathVariable("id") Long id){
        if(id==entity.getId())
        {imageDAO.save(entity);
        return new ResponseEntity<>("Update completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(imageDAO.getByID(id)!=null){
            imageDAO.delete(id);
            return new ResponseEntity<>("Deleted completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Deleted Fail",HttpStatus.BAD_REQUEST);
    }
}
