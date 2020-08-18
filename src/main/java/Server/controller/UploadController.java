package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.UploadDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.UploadEntity;
import Server.model.DTO.APIAccountDTO;
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
    UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> postImage(@RequestBody UploadInDTO uploadInDTO, @RequestHeader String userToken) {
        if (uploadInDTO == null || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(uploadInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        UploadEntity entity = uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateAccount(@RequestBody UploadInDTO uploadInDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (uploadInDTO == null || apiAccountDAO.checkToken(uploadInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(uploadInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (uploadDAO.getByID(id) != null)
            uploadDAO.save(uploadInDTO.getUploadEntity());
        return new ResponseEntity<>("Update Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        uploadDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/Get/{id}",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(uploadDAO.getByID(id), HttpStatus.OK);
    }
}
