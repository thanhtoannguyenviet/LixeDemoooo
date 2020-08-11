package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.DirectorEntity;
import Server.model.DB.DirectorFilmEntity;
import Server.model.DB.LogEntity;
import Server.model.DTO.Criteria;
import Server.model.DTO.DirectorDTO;
import Server.model.DTO.DirectorInDTO;
import Server.model.DTO.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/FilmSite/Director")
@RestController
public class DirectorController {
    @Autowired
    DirectorDAO directorDAO;
    DirectorFilmDAO directorFilmDAO = new DirectorFilmDAO();
    FilmSiteDAO filmSiteDAO = new FilmSiteDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody DirectorInDTO directorInDTO) {
        if (directorInDTO == null || directorInDTO.getApiToken() == null
                || directorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(directorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        DirectorEntity entity = directorDAO.save(directorInDTO.getDirectorEntity());

        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody DirectorInDTO directorInDTO, @PathVariable("id") Long id) {
        if (directorInDTO == null || directorInDTO.getApiToken() == null
                || directorInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(directorInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (directorDAO.getByID(id) != null) {
            directorDAO.save(directorInDTO.getDirectorEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (directorDAO.getByID(id) != null) {
            directorDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        DirectorEntity directorEntity = directorDAO.getByID(id);
        if (directorEntity != null) {
            return new ResponseEntity<>(getDirectorDTO(directorEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody String apiToken, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(DirectorEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<DirectorEntity> directorEntityList = directorDAO.loadDataPagination(criteria);
            List<DirectorDTO> directorDTOList = new ArrayList<>();
            if (!directorEntityList.isEmpty()) {
                for (DirectorEntity item : directorEntityList
                ) {
                    DirectorDTO directorDTO = getDirectorDTO(item);
                    if (directorDTO != null)
                        directorDTOList.add(directorDTO);
                }
                return new ResponseEntity<>(directorDTOList, HttpStatus.OK);
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
    public ResponseEntity<?> count(@RequestBody String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(directorDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private DirectorDTO getDirectorDTO(DirectorEntity directorEntity) {
        List<DirectorFilmEntity> directorFilmEntityList = directorFilmDAO.getID("directorid", directorEntity.getId() + "");
        List<FilmDTO> filmDTOList = new ArrayList<>();
        if (!directorFilmEntityList.isEmpty()) {
            for (DirectorFilmEntity item : directorFilmEntityList) {
                FilmDTO filmDTO = filmSiteDAO.getFilmDTOById(item.getFilmid());
                if (filmDTO != null) {
                    filmDTOList.add(filmDTO);
                }
            }
        }
        return new DirectorDTO(directorEntity, filmDTOList);
    }
}
