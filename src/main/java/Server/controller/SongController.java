package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.FilmDTO;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/MusicSite/Song")
@RestController
public class SongController {
    @Autowired
    SongDAO songDAO;
    SongSiteDAO songSiteDAO = new SongSiteDAO();
    APIAccountDAO apiAccountDAO = new APIAccountDAO();
    @RequestMapping(value = "{apiToken}/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@PathVariable("apiToken") String apiToken, @RequestBody SongEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        songDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/Put",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> put(@PathVariable("apiToken") String apiToken, @RequestBody SongEntity entity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        songDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "{apiToken}/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> Get(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SongEntity entity = songDAO.getByID(id);
        if (entity != null)
            return new ResponseEntity<>(songSiteDAO.getSongDTOById(id), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{apiToken}/GetTop{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@PathVariable("apiToken") String apiToken, @PathVariable("id") int id) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SongEntity.class);
            criteria.setTop(id);
            List<SongEntity> ls = songDAO.getTop10(criteria);
            List<SongDTO> songDTOList = new ArrayList<>();
            for (SongEntity item : ls
            ) {
                songDTOList.add(songSiteDAO.getSongDTOById(item));
            }
            return new ResponseEntity<>(Collections.unmodifiableList(songDTOList), HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetTopNew{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTopNew10(@PathVariable("apiToken") String apiToken, @PathVariable("id") int id) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SongEntity.class);
            criteria.setTop(id);
            List<SongEntity> ls = songDAO.getTop10New(criteria);
            List<SongDTO> songDTOList = new ArrayList<>();
            for (SongEntity item : ls
            ) {
                songDTOList.add(songSiteDAO.getSongDTOById(item));
            }
            return new ResponseEntity<>(songDTOList, HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/GetAllHasPage{itemOnPage}/{page}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@PathVariable("apiToken") String apiToken, @PathVariable("page") int page, @PathVariable("itemOnPage") int itemOnPage) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            Criteria criteria = new Criteria();
            criteria.setClazz(SongEntity.class);
            criteria.setCurrentPage(page);
            criteria.setItemPerPage(itemOnPage);
            //SongDTO entity = musicDAO.GetSongDTO(id);
            List<SongEntity> songEntityList = songDAO.loadDataPagination(criteria);
            List<SongDTO> songDTOList = new ArrayList<>();
            for (SongEntity item : songEntityList) {
                songDTOList.add(songSiteDAO.getSongDTOById(item));
            }
            return new ResponseEntity<>(Collections.unmodifiableList(songDTOList), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "{apiToken}/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable("apiToken") String apiToken, @RequestBody SongEntity entity, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (songDAO.getByID(id) != null) {
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Remove/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> remove(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SongEntity entity = songDAO.getByID(id);
        if (entity != null) {
            entity.setActive(false);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Recover/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> recover(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SongEntity entity = songDAO.getByID(id);
        if (entity != null) {
            entity.setActive(true);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Not Found" + entity.getId(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long id) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (songDAO.getByID(id) != null) {
            songDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{apiToken}/PostSinger/{idSong}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> postSinger(@PathVariable("apiToken") String apiToken, @PathVariable("id") Long idsong, @RequestBody SingerEntity singerEntity) {
        if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        SongSingerEntity songSingerEntity = new SongSingerEntity();
        songSingerEntity.setSingerid(singerEntity.getId());
        songSingerEntity.setSongid(idsong);
        return new ResponseEntity<>(songSingerEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "{apiToken}/Count", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> count(@PathVariable("apiToken") String apiToken) {
        try {
            if (apiToken == null || apiToken.isEmpty() || apiAccountDAO.checkToken(apiToken) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(songDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
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
            criteria.setClazz(SongEntity.class);
            criteria.setTop(item);
            List<SongEntity> songEntityList = songDAO.loadTopRandom(criteria);
            List<SongDTO> lsSongDTO = new ArrayList<>();
            for (SongEntity songE : songEntityList) {
                lsSongDTO.add(songSiteDAO.getSongDTOById(songE.getId()));
            }
            if (!songEntityList.isEmpty())
                return new ResponseEntity<>(lsSongDTO, HttpStatus.OK);
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
}
