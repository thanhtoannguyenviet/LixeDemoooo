package Server.controller;

import Server.model.DAO.UserDAO;
import Server.model.DB.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/Account", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> getAccount(){

        return new ResponseEntity<>("", HttpStatus.OK);

    }
    @RequestMapping(value = "/RegisterAPI/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerAPI(@RequestBody UserEntity user){
//       userDAO.Save(user);
        return new ResponseEntity<>("",HttpStatus.CREATED);
    }


}
