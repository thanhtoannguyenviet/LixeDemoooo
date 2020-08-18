package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.BannerDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.UserDAO;
import Server.model.DB.ActorEntity;
import Server.model.DB.BannerEntity;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.ActorDTO;
import Server.model.DTO.BannerInDTO;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/Banner")
@RestController
public class BannerController {
    @Autowired
    BannerDAO bannerDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody BannerInDTO bannerInDTO, @RequestHeader String userToken) {
        if (bannerInDTO == null || apiAccountDAO.checkToken(bannerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(bannerInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        bannerDAO.save(bannerInDTO.getBannerEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@RequestBody BannerInDTO bannerInDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (bannerInDTO == null || apiAccountDAO.checkToken(bannerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(bannerInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (id == bannerInDTO.getBannerEntity().getId()) {
            bannerDAO.save(bannerInDTO.getBannerEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,
                                         @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        if (bannerDAO.getByID(id) != null) {
            bannerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,
                                 @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        BannerEntity banner = bannerDAO.getByID(id);
        if (banner != null)
            return new ResponseEntity<>(banner, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetAll",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        return new ResponseEntity<>(bannerDAO.getAll(), HttpStatus.OK);
    }
}
