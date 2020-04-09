package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SongDTO;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Album")
@RestController
public class AlbumController {
    @Autowired
    AlbumDAO albumDAO;
    SongDAO songDAO;
    AlbumCategoryMusicDAO albumCategoryMusicDAO;
    SongSingerDAO songSingerDAO;
    SingerDAO singerDAO;
    SignalDAO signalDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AlbumEntity entity){
       albumDAO.Save(entity);
       return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody AlbumEntity entity,@PathVariable("id") long id){
        if(id==entity.getId())
       albumDAO.Save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(albumDAO.GetByID(id),HttpStatus.OK);
    }
    @RequestMapping(value = "/Delete/",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody AlbumEntity entity){
        albumDAO.Delete(entity.getId());
        List<AlbumCategorymusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("albumid",entity.getId()+"");
        for(AlbumCategorymusicEntity item : albumCategoryMusicEntity){
            albumCategoryMusicDAO.Delete(item.getId());
        }
        return new ResponseEntity<>("Delete completed",HttpStatus.OK);
    }

//    @RequestMapping(value ="/PostToCategory/{idCategoryMusic}",method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<?>updateToCategoryMusic(@RequestBody AlbumDTO entity, @PathVariable("idCategoryMusic") Long id){
//        List<AlbumCategorymusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("categoryid",id+"");
//        for ( AlbumCategorymusicEntity item : albumCategoryMusicEntity) {
//            if(item.getAlbumid()!=entity.getAlbumEntity().getId()){
//                AlbumCategorymusicEntity newItem = new AlbumCategorymusicEntity();
//                newItem.setAlbumid(entity.getAlbumEntity().getId());
//                newItem.setCatagoryid(id);
//                albumCategoryMusicDAO.Save(newItem);
//            }
//        }
//        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
//    }
@RequestMapping(value ="/PostToCategory/{idCategoryMusic}",method = RequestMethod.POST)
@ResponseBody
public ResponseEntity<?>updateToCategoryMusic(@RequestBody AlbumEntity entity, @PathVariable("idCategoryMusic") Long id){
    AlbumCategorymusicEntity albumCategorymusicEntity = new AlbumCategorymusicEntity();
    albumCategorymusicEntity.setAlbumid(entity.getId());
    albumCategorymusicEntity.setCatagoryid(id);
    albumCategoryMusicDAO.Save(albumCategorymusicEntity);
    return new ResponseEntity<>("Update Completed",HttpStatus.OK);
}
    @RequestMapping(value ="/DeleteToCategory/{idCategoryMusic}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?>updateToCategoryMusic(@PathVariable("idCategoryMusic") Long id){
        albumCategoryMusicDAO.Delete(id);
        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
    }
    @RequestMapping(value ="/Count/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (@PathVariable("id") Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page){
        Criteria criteria = new Criteria();
        criteria.setClazz(AlbumDTO.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(signalDAO.findData(criteria),HttpStatus.OK);
    }
}
