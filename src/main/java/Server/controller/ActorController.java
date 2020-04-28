    package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.ActorDTO;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("api/FilmSite/Actor")
@RestController
public class ActorController {
    @Autowired
    ActorDAO actorDAO;
    FilmActorDAO filmActorDAO = new FilmActorDAO();
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody ActorEntity actor){
        actorDAO.save(actor);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateActor (@RequestBody ActorEntity actor, @PathVariable("id") Long id){
        if(id==actor.getId())
        {
            actorDAO.save(actor);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        if(actorDAO.getByID(id)!=null){
            actorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        ActorDTO actorDTO = getActorDTO(actorDAO.getByID(id));
        return new ResponseEntity<>(actorDTO,HttpStatus.OK);
    }
    @RequestMapping(value = "/PostToFilm/{id}",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> posttofilm(@PathVariable("id") Long id,@RequestBody ActorEntity actor){
        FilmActorEntity filmActorEntity = new FilmActorEntity();
        filmActorEntity.setFilmid(id);
        filmActorEntity.setActorid(actor.getId());
        filmActorDAO.save(filmActorEntity);
        return new ResponseEntity<>(actorDAO.getByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/PutToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id,@RequestBody FilmActorEntity entity){
        FilmActorEntity filmActorEntity = filmActorDAO.getByID(id);
        if(filmActorEntity.getFilmid()==entity.getId())
        filmActorDAO.save(entity);
        return new ResponseEntity<>("Post Completed",HttpStatus.OK);
    }
    @RequestMapping(value = "/RemoveToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id){
        filmActorDAO.delete(id);
        return new ResponseEntity<>("Post Completed",HttpStatus.OK);
    }
@RequestMapping(value ="/GetAllHasPage{item}/{page}", method = RequestMethod.GET)
@ResponseBody
public  ResponseEntity<?> getAllHasPage (@PathVariable("page") int page,@PathVariable("item") int item) {
    try {
        Criteria criteria = new Criteria();
        criteria.setClazz(ActorEntity.class);
        criteria.setCurrentPage(page);
        criteria.setItemPerPage(item);
        return new ResponseEntity<>(actorDAO.loadDataPagination(criteria), HttpStatus.OK);
    } catch (Exception e) {
        LogEntity log = new LogEntity(e);
        (new LogDAO()).save(log);
        e.printStackTrace();
        return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
    }
}
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count () {
        try {
            return new ResponseEntity<>(actorDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetRandom{item}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getRandom (@PathVariable("item") int item) {
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(ActorEntity.class);
            criteria.setTop(item);
            return new ResponseEntity<>(actorDAO.loadTopRandom(criteria), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    private ActorDTO getActorDTO(ActorEntity actorEntity){
        List<FilmActorEntity> filmActorEntityList= filmActorDAO.getListHasCondition("actorid",actorEntity.getId()+"");
        FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
        List<FilmDTO> filmDTOList = new ArrayList<>();
        for ( FilmActorEntity item : filmActorEntityList) {
            filmDTOList.add(filmSiteDAO.getFilmDTOById(item.getFilmid()));
        }
        return new ActorDTO(actorEntity, Collections.unmodifiableList(filmDTOList));
    }
}

