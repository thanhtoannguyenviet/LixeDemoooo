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
    SongDAO songDAO;
    ImageDAO imageDAO;
    SongSingerDAO songSingerDAO;
    MusicDAO musicDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SingerDTO singerDTO){
        SingerEntity singerEntity = singerDAO.Save(singerDTO.getSingerEntity());
        singerDTO.getImageEntity().setEntryid(singerEntity.getId());
        imageDAO.Save(singerDTO.getImageEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Get/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        SingerEntity singerEntity = singerDAO.GetByID(id);
        ImageEntity imageEntity = imageDAO.GetId("Singer",id).get(0);
        List<SongDTO> songDTOList = new ArrayList<>();
        List<SongSingerEntity> songSingerEntity = songSingerDAO.GetId("singerid",id+"");
        for ( SongSingerEntity item :songSingerEntity) {
            songDTOList.add(musicDAO.GetSongDTO(item.getSongid()));
        }
        SingerDTO singerDTO = new SingerDTO(singerEntity,songDTOList,imageEntity,new Criteria());
        return new ResponseEntity<>(singerDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/Delete/",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody SingerDTO singerDTO){
        singerDAO.Delete(singerDTO.getSingerEntity().getId());
        imageDAO.Delete(singerDTO.getImageEntity().getId());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }

}
