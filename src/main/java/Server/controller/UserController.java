package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.UserEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.UserDTO;
import Server.model.DTO.UserInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/Account/")
@RestController
public class UserController {
    @Autowired
    UserDAO userDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    @RequestMapping(value = "/GetAllUsers",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAccount(@RequestBody APIAccountDTO apiAccountDTO) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null
                || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(userDAO.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Register",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserInDTO userInDTO) {
        if (userInDTO == null || userInDTO.getApiToken() == null
                || userInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(userInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userInDTO.getUserEntity() == null || userInDTO.getUserEntity().getUsername() == null
                || userInDTO.getUserEntity().getUsername() == "" || !userDAO.checkUniqueUsername(userInDTO.getUserEntity().getUsername())) {
            return new ResponseEntity<>("Username has already existed.", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userDAO.registerUser(userInDTO.getUserEntity());

        if (user != null) {
            return new ResponseEntity<>("Register successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Register failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetUserById/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getUserByID(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null
                || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        UserDTO user = userDAO.getUserByID(id);
        if (user == null) {
            return new ResponseEntity<>("There is no data found.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/CheckUsername/{username}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable String username) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null
                || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (username == null || username == "" || userDAO.checkUniqueUsername(username)) {
            return new ResponseEntity<>("Username has already existed.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("OK!", HttpStatus.OK);
    }

    @RequestMapping(value = "/Login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserInDTO userInDTO) {
        int apiTokenType = 0;
        if (userInDTO == null || userInDTO.getApiToken() == null || userInDTO.getApiToken().isEmpty()) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        } else {
            apiTokenType = apiAccountDAO.checkToken(userInDTO.getApiToken());
            if (apiTokenType == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
        }

        String userToken = "";
        if (userInDTO.getUserEntity() != null) {
            UserDTO user = userDAO.login(userInDTO.getUserEntity(), apiTokenType);
            if (user != null) {
                if (apiTokenType == 1) {
                    userToken = user.getUserEntity().getUserWebToken();
                } else {
                    userToken = user.getUserEntity().getUserMbToken();
                }
            } else {
                return new ResponseEntity<>("Login failed!", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(userToken, HttpStatus.OK);
    }

    @RequestMapping(value = "/Test",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> searchIdentity(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        int apiTokenType = 0;
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty()) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        } else {
            apiTokenType = apiAccountDAO.checkToken(apiAccountDTO.getApiToken());
            if (apiTokenType == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
        }
        UserEntity user = userDAO.findUserByToken(userToken, apiTokenType);
        if (user == null) {
            return new ResponseEntity<>("No no!", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("OKAY!", HttpStatus.OK);
    }
}