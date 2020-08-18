package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.*;
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
    UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/Post",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SongInDTO songInDTO, @RequestHeader String userToken) {
        if (songInDTO == null || apiAccountDAO.checkToken(songInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(songInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongEntity entity = songDAO.save(songInDTO.getSongEntity());
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Put",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody SongInDTO songInDTO, @RequestHeader String userToken) {
        if (songInDTO == null || apiAccountDAO.checkToken(songInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(songInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        songDAO.save(songInDTO.getSongEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> Get(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongDTO songDTO = songSiteDAO.getSongDTOById(id);

        if (songDTO != null)
            return new ResponseEntity<>(songDTO, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/GetTop{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop10(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") int id,
                                      @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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

    @RequestMapping(value = "/GetTopNew{id}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTopNew10(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") int id,
                                         @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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

    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getPage(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("page") int page,
                                     @PathVariable("itemOnPage") int itemOnPage, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody SongInDTO songInDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (songInDTO == null || apiAccountDAO.checkToken(songInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(songInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (songDAO.getByID(id) != null) {
            songDAO.save(songInDTO.getSongEntity());
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Remove/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> remove(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongEntity entity = songDAO.getByID(id);
        if (entity != null) {
            entity.setActive(false);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Update Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Recover/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> recover(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongEntity entity = songDAO.getByID(id);
        if (entity != null) {
            entity.setActive(true);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Not Found" + entity.getId(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("id") Long id, @RequestHeader String userToken) {
        if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        if (songDAO.getByID(id) != null) {
            songDAO.delete(id);
            return new ResponseEntity<>("Delete Completed", HttpStatus.OK);
        } else return new ResponseEntity<>("Delte Fail", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/PostSinger/{idSong}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity<?> postSinger(@PathVariable("idSong") Long idSong, @RequestBody SingerInDTO singerInDTO, @RequestHeader String userToken) {
        if (singerInDTO == null || apiAccountDAO.checkToken(singerInDTO.getApiToken()) != 1) {
            return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
        }
        if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(singerInDTO.getApiToken())) != 1) {
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
        SongSingerEntity songSingerEntity = new SongSingerEntity();
        songSingerEntity.setSingerid(singerInDTO.getSingerEntity().getId());
        songSingerEntity.setSongid(idSong);
        return new ResponseEntity<>(songSingerEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "/Count", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> count(@RequestBody APIAccountDTO apiAccountDTO, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(songDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/GetRandom{item}",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getRandom(@RequestBody APIAccountDTO apiAccountDTO, @PathVariable("item") int item, @RequestHeader String userToken) {
        try {
            if (apiAccountDTO == null || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            if (userDAO.checkUserRoleId(userToken, apiAccountDAO.checkToken(apiAccountDTO.getApiToken())) == 0) {
                return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
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
    @RequestMapping(value = "/UpdateRange",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> getTop(@RequestBody APIAccountDTO apiAccountDTO) {
        try {
            if (apiAccountDTO == null || apiAccountDTO.getApiToken() == null || apiAccountDTO.getApiToken().isEmpty() || apiAccountDAO.checkToken(apiAccountDTO.getApiToken()) == 0) {
                return new ResponseEntity<>("Token is not valid.", HttpStatus.FORBIDDEN);
            }
            List<SongEntity> songEntityList = songDAO.getWithIndex("song");
            if(songEntityList!=null){
                for(int i=0;i<songEntityList.size();i++){
                    SongEntity songEntity =  songEntityList.get(i);
                    songEntity.setRange(i+1);
                    songDAO.save(songEntity);
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
}
