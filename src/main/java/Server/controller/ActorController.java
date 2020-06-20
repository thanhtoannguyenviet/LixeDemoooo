package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.ActorDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/FilmSite/Actor")
@RestController
public class ActorController {
    @Autowired
    ActorDAO actorDAO;
    FilmActorDAO filmActorDAO = new FilmActorDAO();
    ImageDAO imageDAO = new ImageDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "{apiToken}/Post/",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@PathVariable("apiToken") String apiToken, @RequestBody ActorEntity actor) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        actor =actorDAO.save(actor);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@PathVariable("apiToken") String apiToken, @RequestBody ActorEntity actor, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }

        if (id == actor.getId()) {
            actorDAO.save(actor);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteActor(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (actorDAO.getByID(id) != null) {
            actorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        ActorDTO actorDTO = getActorDTO(actorDAO.getByID(id));
        if (actorDTO != null)
            return new ResponseEntity<>(actorDTO, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{apiToken}/PostToFilm/{id}",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> posttofilm(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id, @RequestBody ActorEntity actor) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmActorEntity filmActorEntity = new FilmActorEntity();
        filmActorEntity.setFilmid(id);
        filmActorEntity.setActorid(actor.getId());
        filmActorDAO.save(filmActorEntity);
        return new ResponseEntity<>(actorDAO.getByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/PutToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id, @RequestBody FilmActorEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmActorEntity filmActorEntity = filmActorDAO.getByID(id);
        if (filmActorEntity.getFilmid() == entity.getId())
            filmActorDAO.save(entity);
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/RemoveToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        filmActorDAO.delete(id);
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/GetAllHasPage{item}/{page}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAllHasPage(@PathVariable("apiToken") String apiToken, @PathVariable("page") int page, @PathVariable("item") int item) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(ActorEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(item);
            List<ActorEntity> ls = actorDAO.loadDataPagination(criteria);
            List<ActorDTO> lsResult = new ArrayList<>();
            if (!ls.isEmpty()) {
                for (ActorEntity actor : ls
                ) {
                    lsResult.add(getActorDTO(actor));
                }
                return new ResponseEntity<>(lsResult, HttpStatus.OK);
            } else return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/Count",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@PathVariable("apiToken") String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(actorDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetRandom{item}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getRandom(@PathVariable("apiToken") String apiToken, @PathVariable("item") int item) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(ActorEntity.class);
            criteria.setTop(item);
            List<ActorEntity> actorEntityList = actorDAO.loadTopRandom(criteria);
            List<ActorDTO> actorDTOList = new ArrayList<>();
            if (!actorEntityList.isEmpty()) {
                for (ActorEntity aItem : actorEntityList) {
                    actorDTOList.add(getActorDTO(aItem));
                }
                return new ResponseEntity<>(actorEntityList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private ActorDTO getActorDTO(ActorEntity actorEntity) {
        List<FilmActorEntity> filmActorEntityList = filmActorDAO.getListHasCondition("actorid", actorEntity.getId() + "");
        List<ImageEntity> imageEntities = imageDAO.getId("actor", actorEntity.getId());
        ImageEntity imageEntity = null;
        if (!imageEntities.isEmpty()) {
            imageEntity = imageEntities.get(0);
        }
        FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
        List<FilmDTO> filmDTOList = new ArrayList<>();
        if (!filmActorEntityList.isEmpty()) {
            for (FilmActorEntity item : filmActorEntityList) {
                FilmDTO film = filmSiteDAO.getFilmDTOById(item.getFilmid());
                if (film != null)
                    filmDTOList.add(film);
            }
        }
        return new ActorDTO(actorEntity, imageEntity, filmDTOList);
    }
}

