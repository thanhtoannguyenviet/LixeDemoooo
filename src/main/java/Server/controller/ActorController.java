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
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> registerActor(@RequestBody ActorInDTO actorInDTO, @RequestHeader String userToken) {
        if (actorInDTO == null || apiAccountDAO.checkToken(actorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(actorInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

        ActorEntity actor = actorDAO.save(actorInDTO.getActorEntity());
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@RequestBody ActorInDTO actorInDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (actorInDTO == null || apiAccountDAO.checkToken(actorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(actorInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

        if (id == actorInDTO.getActorEntity().getId()) {
            ActorEntity actor = actorDAO.save(actorInDTO.getActorEntity());
            return new ResponseEntity<>(actor, HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteActor(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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
    public ResponseEntity<?> get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        ActorDTO actorDTO = getActorDTO(actorDAO.getByID(id));
        if (actorDTO != null)
            return new ResponseEntity<>(actorDTO, HttpStatus.OK);
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/PostToFilm/{id}",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> posttofilm(@PathVariable("id") Long id, @RequestBody ActorInDTO actorInDTO, @RequestHeader String userToken) {
        if (actorInDTO == null || apiAccountDAO.checkToken(actorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(actorInDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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
    public ResponseEntity<?> putToFilm(@PathVariable("id") Long id, @RequestBody FilmActorInDTO entity, @RequestHeader String userToken) {
        if (entity == null || apiAccountDAO.checkToken(entity.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(entity.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        FilmActorEntity filmActorEntity = filmActorDAO.getByID(id);
        if (filmActorEntity.getFilmid() == entity.getFilmActorEntity().getId())
            filmActorDAO.save(entity.getFilmActorEntity());
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/RemoveToFilm/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> putToFilm(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
        filmActorDAO.delete(id);
        return new ResponseEntity<>("Post Completed", HttpStatus.OK);
    }

    @RequestMapping(value = "/GetAllHasPage{item}/{page}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getAllHasPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page,
                                           @PathVariable("item") int item, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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
    public ResponseEntity<?> getRandom(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("item") int item,
                                       @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
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

