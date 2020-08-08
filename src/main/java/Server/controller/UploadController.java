package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.UploadDAO;
import Server.model.DB.UploadEntity;
import Server.model.DTO.UploadInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("File")
@RestController
public class UploadController {
    @Autowired
    UploadDAO uploadDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> postImage(@RequestBody UploadInDTO uploadInDTO) {
        if (uploadInDTO == null || uploadInDTO.getApiToken() == null
                || uploadInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        UploadEntity entity = uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody UploadInDTO uploadInDTO, @PathVariable("id") Long id) {
        if (uploadInDTO == null || uploadInDTO.getApiToken() == null
                || uploadInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (uploadDAO.getByID(id) != null)
            uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        uploadDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Get/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(uploadDAO.getByID(id), HttpStatus.OK);
    }
}
