package Server.controller;

import Server.model.DAO.ImageDAO;
import Server.model.DAO.UploadDAO;
import Server.model.DB.ImageEntity;
import Server.model.DB.UploadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("File")
@RestController
public class UploadController {
    @Autowired
    UploadDAO uploadDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postImage(@RequestBody UploadEntity entity){
        uploadDAO.Save(entity);
        HttpHeaders responseHeader=new HttpHeaders();
        URI newAccounUrl= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post Completed",responseHeader, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateAccount(@RequestBody UploadEntity entity, @PathVariable Long id ){
        if(uploadDAO.GetByID(id)!=null)
            uploadDAO.Save(entity);
        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        uploadDAO.Delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
