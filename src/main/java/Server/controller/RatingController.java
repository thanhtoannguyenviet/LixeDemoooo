package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.RatingDAO;
import Server.model.DB.ApiaccountEntity;
import Server.model.DB.RatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable("apiToken") String apiToken, @RequestBody RatingEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        entity.setVotedislike(0);
        entity.setVotelike(0);

        ratingDAO.save(entity);
        HttpHeaders responseHeader = new HttpHeaders();
        URI newAccounUrl = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
        responseHeader.setLocation(newAccounUrl);
        return new ResponseEntity<>("Post completed", responseHeader, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable("apiToken") String apiToken, @RequestBody RatingEntity entity, @PathVariable Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/VoteLike/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> increase(@PathVariable("apiToken") String apiToken, @RequestBody RatingEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            entity.setVotelike(1 + entity.getVotedislike());
            ratingDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/VoteDissLike/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> decrease(@PathVariable("apiToken") String apiToken, @RequestBody RatingEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            entity.setVotedislike(1 + entity.getVotedislike());
            ratingDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (ratingDAO.getByID(id) != null) {
            ratingDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }
}
