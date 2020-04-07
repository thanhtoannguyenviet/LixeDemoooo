package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
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
    MusicDAO musicDAO;
    SongSingerDAO songSingerDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SongEntity entity){
        songDAO.Save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody SongEntity entity){
        songDAO.Save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> Get (@PathVariable("id") Long id){
        SongDTO entity = musicDAO.GetSongDTO(id);
        return new ResponseEntity<>(entity,HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/SelectTop10",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop10 (){
        Criteria criteria = new Criteria();
        criteria.setClazz(SongEntity.class);
        criteria.setTop(10);
        //SongDTO entity = musicDAO.GetSongDTO(id);
        return new ResponseEntity<>(SignalDAO.findData(criteria),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/GetSongPage/{page}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        Criteria criteria = new Criteria();
        criteria.setClazz(SongEntity.class);
        criteria.setCurrentPage(page-1);
        //SongDTO entity = musicDAO.GetSongDTO(id);
        return new ResponseEntity<>(SignalDAO.findData(criteria),HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> update (@RequestBody SongEntity entity, @PathVariable Long id){
        if(songDAO.GetByID(id)!=null)
        {
            songDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "Remove/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public  ResponseEntity<?> remove (@PathVariable Long id){
        SongEntity entity=songDAO.GetByID(id);
        if(entity!=null)
        {
            entity.setActive(false);
            songDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "Recover/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> recover ( @PathVariable Long id){
        SongEntity entity=songDAO.GetByID(id);
        if(entity!=null)
        {
            entity.setActive(true);
            songDAO.Save(entity);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Not Found" + entity.getId(),HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(songDAO.GetByID(id)!=null){
            songDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/test", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> getAccount(){
        Criteria criteria = new Criteria();
        criteria.setTop(10);
        criteria.setClazz(SongEntity.class);
        return new ResponseEntity<>(SignalDAO.findData(criteria), HttpStatus.OK);
    }
    @RequestMapping(value = "PostSinger/{idSong}",method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<?> postSinger(@PathVariable Long idsong,@RequestBody SingerEntity singerEntity){
        SongSingerEntity songSingerEntity = new SongSingerEntity();
        songSingerEntity.setSingerid(singerEntity.getId());
        songSingerEntity.setSongid(idsong);
        songSingerDAO.Save(songSingerEntity);
        return new ResponseEntity<>(songSingerEntity,HttpStatus.OK);
    }
}
