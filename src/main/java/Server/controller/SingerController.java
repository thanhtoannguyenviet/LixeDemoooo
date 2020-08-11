package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.Criteria;
import Server.model.DTO.SingerDTO;
import Server.model.DTO.SingerInDTO;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Singer")
@RestController
public class SingerController {
    @Autowired
    SingerDAO singerDAO;
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    SongSiteDAO songSiteDAO = new SongSiteDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();

    @RequestMapping(value = "/Post/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SingerInDTO singerInDTO) {
        if (singerInDTO == null || singerInDTO.getApiToken() == null
                || singerInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(singerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SingerEntity singer = singerDAO.save(singerInDTO.getSingerEntity());
        return new ResponseEntity<>(singer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody SingerInDTO singerInDTO, @PathVariable("id") Long id) {
        if (singerInDTO == null || singerInDTO.getApiToken() == null
                || singerInDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(singerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (singerDAO.getByID(id) != null) {
            singerDAO.save(singerInDTO.getSingerEntity());
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
        if (singerDAO.getByID(id) != null) {
            singerDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delete Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@RequestBody String apiToken, @PathVariable("id") Long id) {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
        return new ResponseEntity<>(singerDAO.getByID(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/GetTop{itop}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@RequestBody String apiToken, @PathVariable("itop") int itop) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SingerEntity.class);
            criteria.setTop(itop);
            List<SingerEntity> singerEntityList = singerDAO.getTop10(criteria);
            List<SingerDTO> singerDTOList = new ArrayList<>();
            if (!singerEntityList.isEmpty()) {
                for (SingerEntity item : singerEntityList) {
                    singerDTOList.add(getSingerDTO(item));
                }
                return new ResponseEntity<>(singerDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody String apiToken, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SingerEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            List<SingerEntity> singerEntityList = singerDAO.loadDataPagination(criteria);
            List<SingerDTO> singerDTOList = new ArrayList<>();
            if (!singerEntityList.isEmpty()) {
                for (SingerEntity item : singerEntityList) {
                    singerDTOList.add(getSingerDTO(item));
                }
                return new ResponseEntity<>(singerDTOList, HttpStatus.OK);
            }
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/Count", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(singerDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    private SingerDTO getSingerDTO(SingerEntity singerEntity) {
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("singerid", singerEntity.getId() + "");
        List<SongDTO> songDTOList = new ArrayList<>();
        for (SongSingerEntity item : songSingerEntityList
        ) {
            SongDTO songDTO = songSiteDAO.getSongDTOById(item.getSongid());
            if (songDTO != null)
                songDTOList.add(songDTO);
        }
        SingerDTO singerDTO = new SingerDTO(singerEntity, songDTOList);
        return singerDTO;
    }
}
