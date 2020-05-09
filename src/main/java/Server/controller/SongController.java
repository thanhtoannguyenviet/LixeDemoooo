package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
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
    @Autowired
    SongSiteDAO songSiteDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SongEntity entity){
        songDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody SongEntity entity){
        songDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> Get (@PathVariable("id") Long id){
        SongEntity entity =  songDAO.getByID(id);
        if(entity!=null)
            return new ResponseEntity<>(songSiteDAO.getSongDTOById(id),HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/GetTop{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop10 (@PathVariable("id") int id) {
        try {
            Criteria criteria = new Criteria();
            criteria.setClazz(SongEntity.class);
            criteria.setTop(id);
            List<SongEntity> ls = songDAO.getTop10(criteria);
            List<SongDTO> songDTOList = new ArrayList<>();
            for ( SongEntity item : ls
                 ) {
                songDTOList.add(songSiteDAO.getSongDTOById(item));
            }
            return new ResponseEntity<>(Collections.unmodifiableList(songDTOList), HttpStatus.OK);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
        }
    }
        @RequestMapping(value = "/GetTopNew{id}",
                method = RequestMethod.GET)
        @ResponseBody
        public  ResponseEntity<?> getTopNew10 (@PathVariable("id") int  id) {
            try {
                Criteria criteria = new Criteria();
                criteria.setClazz(SongEntity.class);
                criteria.setTop(id);
                List<SongEntity> ls = songDAO.getTop10New(criteria);
                List<SongDTO> songDTOList = new ArrayList<>();
                for ( SongEntity item : ls
                ) {
                    songDTOList.add(songSiteDAO.getSongDTOById(item));
                }
                return new ResponseEntity<>(songDTOList, HttpStatus.OK);
            }
            catch (Exception e) {
                LogEntity log = new LogEntity(e);
                (new LogDAO()).save(log);
                e.printStackTrace();
                return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg", HttpStatus.BAD_REQUEST);
            }
        }
    @RequestMapping(value = "/GetAllHasPage{itemOnPage}/{page}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page,@PathVariable("itemOnPage") int itemOnPage){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(SongEntity.class);
        criteria.setCurrentPage(page);
        criteria.setItemPerPage(itemOnPage);
        //SongDTO entity = musicDAO.GetSongDTO(id);
            List<SongEntity> songEntityList = songDAO.loadDataPagination(criteria);
            List<SongDTO> songDTOList = new ArrayList<>();
            for ( SongEntity item : songEntityList) {
                songDTOList.add( songSiteDAO.getSongDTOById(item));
            }
        return new ResponseEntity<>(Collections.unmodifiableList(songDTOList),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody SongEntity entity, @PathVariable("id") Long id){
        if(songDAO.getByID(id)!=null)
        {
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "Remove/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public  ResponseEntity<?> remove (@PathVariable("id") Long id){
        SongEntity entity=songDAO.getByID(id);
        if(entity!=null)
        {
            entity.setActive(false);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "Recover/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> recover ( @PathVariable("id") Long id){
        SongEntity entity=songDAO.getByID(id);
        if(entity!=null)
        {
            entity.setActive(true);
            songDAO.save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Not Found" + entity.getId(),HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(songDAO.getByID(id)!=null){
            songDAO.delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "PostSinger/{idSong}",method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> postSinger(@PathVariable("id") Long idsong,@RequestBody SingerEntity singerEntity){
        SongSingerEntity songSingerEntity = new SongSingerEntity();
        songSingerEntity.setSingerid(singerEntity.getId());
        songSingerEntity.setSongid(idsong);
        return new ResponseEntity<>(songSingerEntity,HttpStatus.OK);
    }
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        try {
            return new ResponseEntity<>(songDAO.count(), HttpStatus.OK);
        } catch (Exception e) {
            new LogDAO().save(new LogEntity(e));
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
            }
    }
    @RequestMapping(value = "/GetAll",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getAll (){
            return new ResponseEntity<>(songDAO.getAll2(),HttpStatus.ACCEPTED);
    }

}
