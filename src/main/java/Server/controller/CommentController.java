package Server.controller;

import Server.model.DAO.CommentDAO;
import Server.model.DB.CommentEntity;
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
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> postCategorySong(@RequestBody CommentEntity entity){
        commentDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateCategorySong (@RequestBody CommentEntity entity, @PathVariable("id") Long id){
        if(entity.getId()==id)
        {
            commentDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(commentDAO.getByID(id)!=null){
            commentDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value="/GetComment/model={model}&id={id}",
        method = RequestMethod.GET,  produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getComment(@PathVariable("id") Long id,@PathVariable("model") String model){
        List<CommentEntity> commentEntityList = commentDAO.getId(model,id);
        if(!commentEntityList.isEmpty()){
            return new ResponseEntity<>(commentEntityList,HttpStatus.OK);
        }
        return new ResponseEntity<>("Please create first comment",HttpStatus.OK);
    }
}
