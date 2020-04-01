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
    public ResponseEntity<?> put(@RequestBody AlbumEntity entity,@PathVariable long id){
        if(id==entity.getId())
       albumDAO.Save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable Long id){
        AlbumEntity albumEntity = albumDAO.GetByID(id);
        String[] arr = albumEntity.getListsongid().split(",");
        List<SongEntity> songDTOList = new ArrayList<>();

        for(String item : arr){
            if(item!=null)
                songDTOList.add(songDAO.GetByID(Long.parseLong(item)));
        }
        List<SongSingerEntity> lsSongSingerEntity =
                songSingerDAO.GetId("songid",songDTOList.get(0).getId()+"" );
        List<SingerEntity> singerEntityList = new ArrayList<>();
        for (SongSingerEntity item : lsSongSingerEntity) {
            singerEntityList.add(singerDAO.GetByID(item.getSingerid()));
        }
        AlbumDTO albumDTO = new AlbumDTO(albumEntity,songDTOList,singerEntityList,new Criteria());
        return new ResponseEntity<>(albumDTO,HttpStatus.OK);
    }
    @RequestMapping(value = "/Delete/",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody AlbumEntity entity){
        albumDAO.Delete(entity.getId());
        List<AlbumCategoryMusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("albumid",entity.getId()+"");
        for(AlbumCategoryMusicEntity item : albumCategoryMusicEntity){
            albumCategoryMusicDAO.Delete(item.getId());
        }
        return new ResponseEntity<>("Delete completed",HttpStatus.OK);
    }

    @RequestMapping(value ="/PostToCategory/{idCategoryMusic}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?>updateToCategoryMusic(@RequestBody AlbumDTO entity, @PathVariable("idCategoryMusic") Long id){
        List<AlbumCategoryMusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("categoryid",id+"");
        for ( AlbumCategoryMusicEntity item : albumCategoryMusicEntity) {
            if(item.getAlbumid()!=entity.getAlbumEntity().getId()){
                AlbumCategoryMusicEntity newItem = new AlbumCategoryMusicEntity();
                newItem.setAlbumid(entity.getAlbumEntity().getId());
                newItem.setCatagoryid(id);
                albumCategoryMusicDAO.Save(newItem);
            }
        }
        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
    }
    @RequestMapping(value ="/Count/{id}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (@PathVariable("id") Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value ="/GetAllHasPage/{page}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable int page){
        Criteria criteria = new Criteria();
        criteria.setClazz(AlbumDTO.class);
        criteria.setCurrentPage(page);
        return new ResponseEntity<>(criteria,HttpStatus.OK);
    }
}
