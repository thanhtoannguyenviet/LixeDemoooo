package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.LogDAO;
import Server.model.DB.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/Log")
@RestController
public class LogController {
    @Autowired
    LogDAO logDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody LogEntity logEntity) {
        logDAO.save(logEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
}
