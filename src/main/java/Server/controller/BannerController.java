package Server.controller;

import Server.model.DAO.BannerDAO;
import Server.model.DAO.LogDAO;
import Server.model.DB.ActorEntity;
import Server.model.DB.BannerEntity;
import Server.model.DB.FilmActorEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.ActorDTO;
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
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody BannerEntity banner){
        bannerDAO.save(banner);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateActor (@RequestBody BannerEntity banner, @PathVariable("id") Long id){
        if(id==banner.getId())
        {
            bannerDAO.save(banner);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(bannerDAO.getByID(id)!=null){
            bannerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        BannerEntity banner = bannerDAO.getByID(id);
        if(banner!=null)
            return new ResponseEntity<>(banner,HttpStatus.OK);
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/GetAll",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(bannerDAO.getAll(),HttpStatus.OK);
    }
}
