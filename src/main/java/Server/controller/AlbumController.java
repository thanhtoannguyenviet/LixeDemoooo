package Server.controller;

import Server.model.DAO.AlbumDAO;
import Server.model.DAO.ImageDAO;
import Server.model.DAO.SingerDAO;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/MusicSite/Album")
@RestController
public class AlbumController {
    @Autowired
    AlbumDAO albumDAO;
    ImageDAO imageDAO;
    SingerDAO singerDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AlbumDTO albumDTO){
        albumDAO.Save(albumDTO.getAlbumEntity());
        imageDAO.Save(albumDTO.getImageEntity());
        if(albumDTO.getSingerEntity()!=null)
            singerDAO.Save(albumDTO.getSingerEntity());
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
   }
