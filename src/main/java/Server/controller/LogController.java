package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.LogDAO;
import Server.model.DB.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/Log")
@RestController
public class LogController {
    @Autowired
    LogDAO logDAO;
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@PathVariable("apiToken") String apiToken, @RequestBody LogEntity logEntity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        logDAO.save(logEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
}
