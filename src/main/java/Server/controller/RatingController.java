package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.RatingDAO;
import Server.model.DB.ApiaccountEntity;
import Server.model.DB.RatingEntity;
import Server.model.DTO.APIAccountDTO;
import Server.model.DTO.RatingInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("Rating")
@RestController
public class RatingController {
    @Autowired
    RatingDAO ratingDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody RatingInDTO ratingInDTO) {
        if (ratingInDTO == null || ratingInDTO.getApiToken() == null
                || ratingInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(ratingInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        ratingInDTO.getRatingEntity().setVotedislike(0);
        ratingInDTO.getRatingEntity().setVotelike(0);

        RatingEntity entity = ratingDAO.save(ratingInDTO.getRatingEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody RatingInDTO ratingInDTO, @PathVariable Long id) {
        if (ratingInDTO == null || ratingInDTO.getApiToken() == null
                || ratingInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(ratingInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingDAO.save(ratingInDTO.getRatingEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/VoteLike/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> increase(@RequestBody RatingInDTO ratingInDTO, @PathVariable("id") Long id) {
        if (ratingInDTO == null || ratingInDTO.getApiToken() == null
                || ratingInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(ratingInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingInDTO.getRatingEntity().setVotelike(1 + ratingInDTO.getRatingEntity().getVotedislike());
            ratingDAO.save(ratingInDTO.getRatingEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/VoteDissLike/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> decrease(@RequestBody RatingInDTO ratingInDTO, @PathVariable("id") Long id) {
        if (ratingInDTO == null || ratingInDTO.getApiToken() == null
                || ratingInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(ratingInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingInDTO.getRatingEntity().setVotedislike(1 + ratingInDTO.getRatingEntity().getVotedislike());
            ratingDAO.save(ratingInDTO.getRatingEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id) {
        if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }
}
