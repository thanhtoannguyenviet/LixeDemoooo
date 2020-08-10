package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
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

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody ActorInDTO actorInDTO) {
        if (actorInDTO == null || actorInDTO.getApiToken() == null || actorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(actorInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        ActorEntity actor = actorDAO.save(actorInDTO.getActorEntity());
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@RequestBody ActorInDTO actorInDTO, @PathVariable("id") Long id) {
        if (actorInDTO == null || actorInDTO.getApiToken() == null || actorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(actorInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }

        if (id == actorInDTO.getActorEntity().getId()) {
            ActorEntity actor = actorDAO.save(actorInDTO.getActorEntity());
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (actorDAO.getByID(id) != null) {
            actorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        ActorDTO actorDTO = getActorDTO(actorDAO.getByID(id));
        if (actorDTO != null)
            return new ResponseEntity<>(actorDTO, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/PostToFilm/{id}",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> posttofilm(@PathVariable("id") Long id, @RequestBody ActorInDTO actorInDTO) {
        if (actorInDTO == null || actorInDTO.getApiToken() == null || actorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(actorInDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmActorEntity filmActorEntity = new FilmActorEntity();
        filmActorEntity.setFilmid(id);
        filmActorEntity.setActorid(actorInDTO.getActorEntity().getId());
        filmActorDAO.save(filmActorEntity);
        return new ResponseEntity<>(actorDAO.getByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/PutToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id, @RequestBody FilmActorInDTO entity) {
        if (entity == null || entity.getApiToken() == null || entity.getApiToken().isEmpty() || apiAccountDAO.checkToken(entity.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        FilmActorEntity filmActorEntity = filmActorDAO.getByID(id);
        if (filmActorEntity.getFilmid() == entity.getFilmActorEntity().getId())
            filmActorDAO.save(entity.getFilmActorEntity());
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/RemoveToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        filmActorDAO.delete(id);
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/GetAllHasPage{item}/{page}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAllHasPage(@RequestBody String apiToken, @PathVariable("page") int page, @PathVariable("item") int item) {
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

    @RequestMapping(value = "/Count",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody String apiToken) {
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

    @RequestMapping(value = "/GetRandom{item}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getRandom(@RequestBody String apiToken, @PathVariable("item") int item) {
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
        List<FilmActorEntity> filmActorEntityList = filmActorDAO.getListHasCondition("actorId", actorEntity.getId() + "");
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

