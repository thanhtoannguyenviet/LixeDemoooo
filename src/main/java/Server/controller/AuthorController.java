package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.AuthorEntity;
import Server.model.DB.LogEntity;
import Server.model.DB.SongEntity;
import Server.model.DTO.*;
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
	UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AuthorInDTO authorInDTO, @RequestHeader String userToken) {
        if (authorInDTO == null || apiAccountDAO.checkToken(authorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(authorInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AuthorEntity entity = authorDAO.save(authorInDTO.getAuthorEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,
                                    @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (authorDAO.getByID(id) != null) {
            authorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetAll",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<?> GetAll(@RequestBody APIAccountDTO apiAccountDTO,
                                    @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(authorDAO.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateActor(@RequestBody AuthorInDTO authorInDTO, @PathVariable("id") Long id,
                                         @RequestHeader String userToken) {
        if (authorInDTO == null || apiAccountDAO.checkToken(authorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(authorInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (id == authorInDTO.getAuthorEntity().getId()) {
            authorDAO.save(authorInDTO.getAuthorEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getDetail(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id,
                                       @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        AuthorEntity authorEntity = authorDAO.getByID(id);
        if (authorEntity != null) {
            return new ResponseEntity<>(getAuthorDTO(authorEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetAllHasPage{item}/{page}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page,
                                     @PathVariable("item") int item, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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

    @RequestMapping(value = "/Count", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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
