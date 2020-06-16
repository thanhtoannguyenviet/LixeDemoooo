package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.UploadDAO;
import Server.model.DB.UploadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("File")
@RestController
public class UploadController {
    @Autowired
    UploadDAO uploadDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postImage(@PathVariable("apiToken") String apiToken, @RequestBody UploadEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        uploadDAO.save(entity);
        return new ResponseEntity<>("Post Completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@PathVariable("apiToken") String apiToken, @RequestBody UploadEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (uploadDAO.getByID(id) != null)
            uploadDAO.save(entity);
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        uploadDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/Get/{id}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(uploadDAO.getByID(id), HttpStatus.OK);
    }
}
