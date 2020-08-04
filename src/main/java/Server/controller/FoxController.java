package Server.controller;

import Server.model.DAO.APIAccountDAO;
import Server.model.DAO.FoxDAO;
import Server.model.DB.SongEntity;
import Server.model.DTO.FoxDTO;
import Server.model.DTO.FoxInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/Test/Fox")
@RestController
public class FoxController {
    @Autowired
    FoxDAO foxDAO = new FoxDAO();

    @RequestMapping(value = "/getAll",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAll(@RequestBody FoxDTO foxDTO) {
        if (foxDTO == null || foxDTO.getKey() == null || !"daKey".equalsIgnoreCase(foxDTO.getKey())) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(foxDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll2",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAll2(@RequestBody FoxInDTO foxInDTO) {
        if (foxInDTO == null || foxInDTO.getApiToken() == null || !"daApiToken".equalsIgnoreCase(foxInDTO.getApiToken())) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(foxDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createOne",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> createOne(@RequestBody FoxInDTO foxInDTO) {
        if (foxInDTO == null || foxInDTO.getApiToken() == null || !"daApiToken".equalsIgnoreCase(foxInDTO.getApiToken())) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FoxDTO in = foxInDTO.getFoxObj();
        FoxDTO result = foxDAO.createOne(in);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll3/{param}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAll3(@PathVariable String param, @RequestBody FoxInDTO foxInDTO) {
        if (!"test".equalsIgnoreCase(param)) {
            return new ResponseEntity<>("Param doesn't pass.", HttpStatus.FORBIDDEN);
        }
        if (foxInDTO == null || foxInDTO.getApiToken() == null || !"daApiToken".equalsIgnoreCase(foxInDTO.getApiToken())) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FoxDTO in = foxInDTO.getFoxObj();
        FoxDTO result = foxDAO.createOne(in);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getString",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> getString(@RequestBody String apiToken) {
        if (apiToken == null || !"daApiToken".equalsIgnoreCase(apiToken)) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("Okay succeed!", HttpStatus.OK);
    }
}
