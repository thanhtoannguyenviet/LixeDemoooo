package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.AuthorEntity;
import Server.model.DB.LogEntity;
import Server.model.DB.SongEntity;
import Server.model.DTO.AuthorDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequestMapping("api/MusicSite/Author")
@RestController
public class AuthorController {
    @Autowired
    AuthorDAO authorDAO;
    SongDAO songDAO = new SongDAO();
    SongSiteDAO songSiteDAO = new SongSiteDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable("apiToken") String apiToken, @RequestBody AuthorEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        entity = authorDAO.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (authorDAO.getByID(id) != null) {
            authorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/GetAll",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<?> GetAll(@PathVariable("apiToken") String apiToken) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(authorDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@PathVariable("apiToken") String apiToken, @RequestBody AuthorEntity actor, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (id == actor.getId()) {
            authorDAO.save(actor);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        AuthorEntity authorEntity = authorDAO.getByID(id);
        if (authorEntity != null) {
            return new ResponseEntity<>(getAuthorDTO(authorEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{apiToken}/GetAllHasPage{item}/{page}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@PathVariable("apiToken") String apiToken, @PathVariable("page") int page, @PathVariable("item") int item) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(AuthorEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(item);
            List<AuthorEntity> authorEntityList = authorDAO.loadDataPagination(criteria);
            if (!authorEntityList.isEmpty()) {
                List<AuthorDTO> authorDTOList = new ArrayList<>();
                for (AuthorEntity ite : authorEntityList) {
                    authorDTOList.add(getAuthorDTO(ite));
                }
                return new ResponseEntity<>(Collections.unmodifiableList(authorDTOList), HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/Count", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@PathVariable("apiToken") String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(authorDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private AuthorDTO getAuthorDTO(AuthorEntity authorEntity) {
        List<SongEntity> songEntityList = songDAO.getSongByAuthorId("authorid", authorEntity.getId() + "");
        List<SongDTO> songDTOList = new ArrayList<>();
        for (SongEntity item : songEntityList) {
            SongDTO songDTO = songSiteDAO.getSongDTOById(item);
            if (songDTO != null) {
                songDTOList.add(songDTO);
            }
        }
        return new AuthorDTO(authorEntity, Collections.unmodifiableList(songDTOList));
    }
}
