package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    @RequestMapping(value = "/Account", //for testing API
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAccount() {
        return new ResponseEntity<>(userDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/RegisterAPI/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerAPI(@PathVariable("apiToken") String apiToken, @RequestBody UserEntity user) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
//       userDAO.Save(user);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/GetAllUsers",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAllUsers(@PathVariable("apiToken") String apiToken) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        List<UserEntity> list = userDAO.getAllUsers();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/GetUserById/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getUserByID(@PathVariable("apiToken") String apiToken, @PathVariable("id") long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        UserEntity user = userDAO.getUserByID(id);
        if (user.getId() == 0) {
            return new ResponseEntity<>("There is no data found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
