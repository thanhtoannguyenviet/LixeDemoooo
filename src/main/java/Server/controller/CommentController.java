package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.CommentDAO;
import Server.model.DB.CommentEntity;
import Server.model.DTO.CommentInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/Comment")
@RestController
public class CommentController {
    @Autowired
    CommentDAO commentDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CommentInDTO commentInDTO) {
        if (commentInDTO == null || commentInDTO.getApiToken() == null
                || commentInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(commentInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        CommentEntity entity = commentDAO.save(commentInDTO.getCommentEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateCategorySong(@RequestBody CommentInDTO commentInDTO, @PathVariable("id") Long id) {
        if (commentInDTO == null || commentInDTO.getApiToken() == null
                || commentInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(commentInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (commentInDTO.getCommentEntity().getId() == id) {
            commentDAO.save(commentInDTO.getCommentEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (commentDAO.getByID(id) != null) {
            commentDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetComment/model={model}&id={id}",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getComment(@RequestBody String apiToken, @PathVariable("id") Long id, @PathVariable("model") String model) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        List<CommentEntity> commentEntityList = commentDAO.getId(model, id);
        if (!commentEntityList.isEmpty()) {
            return new ResponseEntity<>(commentEntityList, HttpStatus.OK);
        }
        return new ResponseEntity<>("Please create first comment", HttpStatus.OK);
    }
}
