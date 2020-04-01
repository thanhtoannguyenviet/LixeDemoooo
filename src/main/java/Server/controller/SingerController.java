package Server.controller;
import Server.model.DAO.*;
import Server.model.DB.ImageEntity;
import Server.model.DB.SingerEntity;
import Server.model.DB.SongSingerEntity;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SingerDTO;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Singer")
@RestController
public class SingerController {
    @Autowired
    SingerDAO singerDAO;
    SongSingerDAO songSingerDAO;
    SongDAO songDAO;
    ImageEntity imageEntity;
    MusicDAO musicDAO;
    @RequestMapping(value = "/",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registeAccount(@RequestBody SingerEntity singer){
        singerDAO.Save(singer);
       return new ResponseEntity<>("Post completed",HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<?> updateAccount(@RequestBody SingerEntity singer, @PathVariable Long id){
        if(singerDAO.GetByID(id)!=null)
        {
            singerDAO.Save(singer);
            return new ResponseEntity<>("Update Completed",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Update Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Singer/Delete/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id){
        if(singerDAO.GetByID(id)!=null){
            singerDAO.Delete(id);
            return new ResponseEntity<>("Delete Completed",HttpStatus.OK);
        }
        else return  new ResponseEntity<>("Delte Fail",HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/Get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable Long id){
        SingerEntity singerEntity = singerDAO.GetByID(id);
        List<SongSingerEntity>lsSongEntity = songSingerDAO.GetId("singerid",id+"");
        List<SongDTO> lsSongDTO = new ArrayList<>();
        for (SongSingerEntity item:lsSongEntity) {
            lsSongDTO.add(musicDAO.GetSongDTO(item.getSongid()));
        }
        SingerDTO singerDTO = new SingerDTO(singerEntity,lsSongDTO);
        return  new ResponseEntity<>(singerDTO,HttpStatus.OK);
    }
}
