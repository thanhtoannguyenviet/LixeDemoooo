package Server.controller;

import Server.model.DAO.ActorDAO;
import Server.model.DAO.FilmActorDAO;
import Server.model.DAO.LogDAO;
import Server.model.DAO.SignalDAO;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/FilmSite/Actor")
@RestController
public class ActorController {
    @Autowired
    ActorDAO actorDAO;
    FilmActorDAO filmActorDAO;
    SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody ActorEntity actor){
        actorDAO.Save(actor);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateActor (@RequestBody ActorEntity actor, @PathVariable("id") Long id){
        if(id==actor.getId())
        {
            actorDAO.Save(actor);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(actorDAO.GetByID(id)!=null){
            actorDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(actorDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/PostToFilm/{id}",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> posttofilm(@PathVariable("id") Long id,@RequestBody ActorEntity actor){
        FilmActorEntity filmActorEntity = new FilmActorEntity();
        filmActorEntity.setFilmid(id);
        filmActorEntity.setActorid(actor.getId());
        filmActorDAO.Save(filmActorEntity);
        return new ResponseEntity<>(actorDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/PutToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id,@RequestBody FilmActorEntity entity){
        FilmActorEntity filmActorEntity = filmActorDAO.GetByID(id);
        if(filmActorEntity.getFilmid()==entity.getId())
        filmActorDAO.Save(entity);
        return new ResponseEntity<>("Post Completed",HttpStatus.OK);
    }
    @RequestMapping(value = "/RemoveToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id){
        filmActorDAO.Delete(id);
        return new ResponseEntity<>("Post Completed",HttpStatus.OK);
    }
    @RequestMapping(value ="/GetTop10/", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop10 () {
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(ActorEntity.class);
            return new ResponseEntity<>(signalDAO.findData(criteria), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).Save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
}

