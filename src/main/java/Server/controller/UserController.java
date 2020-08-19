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
    public ResponseEntity<?> getAccount(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userDAO.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Register",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserInDTO userInDTO) {
        if (userInDTO == null || apiAccountDAO.checkToken(userInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userInDTO.getUserEntity() == null || userInDTO.getUserEntity().getUsername() == null || userInDTO.getUserEntity().getUsername() == "") {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } else if (!userDAO.checkUniqueUsername(userInDTO.getUserEntity().getUsername())) {
            return new ResponseEntity<>("Username has already existed.", HttpStatus.CONFLICT);
        }

        UserEntity user = userDAO.registerUser(userInDTO.getUserEntity());
        if (user != null) {
            return new ResponseEntity<>("Register successfully.", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Register failed.", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetUserById/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getUserByID(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        UserDTO user = userDAO.getUserByID(id);
        if (user == null) {
            return new ResponseEntity<>("There is no data found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/CheckUsername/{username}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> checkUsername(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable String username) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (username == null || "".equalsIgnoreCase(username)) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } else if (!userDAO.checkUniqueUsername(username)) {
            return new ResponseEntity<>("Username has already existed.", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("OK!", HttpStatus.OK);
    }

    @RequestMapping(value = "/Login",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserInDTO userInDTO) {
        if (userInDTO == null) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);

        int apiTokenType = apiAccountDAO.checkToken(userInDTO.getApiToken());
        if (apiTokenType == 0) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);

        String userToken = "";
        if (userInDTO.getUserEntity() != null) {
//            // 1. Check if there is userToken
//            String postUserToken = apiTokenType == 1 ? userInDTO.getUserEntity().getUserWebToken() : userInDTO.getUserEntity().getUserMbToken();
//            UserEntity uEbyToken = userDAO.findUserByToken(postUserToken, apiTokenType);
//            if (uEbyToken != null) return new ResponseEntity<>("Already login.", HttpStatus.CONFLICT);

            // 2. Login
            UserDTO user = userDAO.login(userInDTO.getUserEntity(), apiTokenType);
            if (user.getUserEntity() != null) {
                if (apiTokenType == 1) {
                    user.getUserEntity().setUserMbToken("");
                } else {
                    user.getUserEntity().setUserWebToken("");
                }
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Login failed!", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Login failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/changePassword",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody UserInDTO userInDTO) {
        if (userInDTO == null) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);

        int apiTokenType = apiAccountDAO.checkToken(userInDTO.getApiToken());
        if (apiTokenType == 0) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);

        int result = userDAO.changePassword(userInDTO);
        if (result == 0) {
            return new ResponseEntity<>("Change password failed!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Password has been changed successfully.", HttpStatus.OK);
    }

//    @RequestMapping(value = "/Test",
//            method = RequestMethod.POST,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public ResponseEntity<?> searchIdentity(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
//        if (apiAccountDTO == null) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//
//        int apiTokenType = apiAccountDAO.checkToken(apiAccountDTO.getApiToken());
//        if (apiTokenType == 0) return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
//
//        UserEntity user = userDAO.findUserByToken(userToken, apiTokenType);
//        if (user == null) {
//            return new ResponseEntity<>("No no!", HttpStatus.FORBIDDEN);
//        }
//
//        return new ResponseEntity<>("OKAY!", HttpStatus.OK);
//    }
}
