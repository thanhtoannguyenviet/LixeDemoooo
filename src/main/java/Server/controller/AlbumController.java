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
    ImageDAO imageDAO;
    SingerDAO singerDAO;
    MusicDAO musicDAO;
    AlbumCategoryMusicDAO albumCategoryMusicDAO;
    private void save(AlbumDTO albumDTO){
        AlbumEntity albumEntity = albumDAO.Save(albumDTO.getAlbumEntity());
        albumDTO.getImageEntity().setEntryid(albumDTO.getAlbumEntity().getId());
        imageDAO.Save(albumDTO.getImageEntity());
    }
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AlbumDTO albumDTO){
        save(albumDTO);
       return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Post",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> posti(@RequestBody AlbumDTO albumDTO){
       save(albumDTO);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Get/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable Long id){
        AlbumEntity albumEntity = albumDAO.GetByID(id);
        String[] arr = albumEntity.getListsongid().split(",");
        List<SongDTO> songDTOList = new ArrayList<>();

        for(String item : arr){
            if(item!=null)
                songDTOList.add(musicDAO.GetSongDTO(Long.parseLong(item)));
        }
        List<SingerEntity> singerEntityList = songDTOList.get(0).getSingerEntityList() ;
        ImageEntity imageEntity = imageDAO.GetId("Album",albumEntity.getId()).get(0);
        AlbumDTO albumDTO = new AlbumDTO(albumEntity,songDTOList,imageEntity,singerEntityList,new Criteria());
        return new ResponseEntity<>(albumDTO,HttpStatus.OK);
    }
    @RequestMapping(value = "/Delete/",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody AlbumDTO entity){
        albumDAO.Delete(entity.getAlbumEntity().getId());
        imageDAO.Delete(entity.getImageEntity().getId());
        List<AlbumCategoryMusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.GetId("albumid",entity.getAlbumEntity().getId()+"");
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
}
