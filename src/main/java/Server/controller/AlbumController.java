package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.AlbumDTO;
import Server.model.DTO.Criteria;
import Server.model.DTO.SongDTO;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/MusicSite/Album")
@RestController
public class AlbumController {
    AlbumDAO albumDAO = new AlbumDAO();
    SongDAO songDAO = new SongDAO();
    AlbumCategoryMusicDAO albumCategoryMusicDAO = new AlbumCategoryMusicDAO();
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    SingerDAO singerDAO = new SingerDAO();
    AlbumSingerDAO albumSingerDAO = new AlbumSingerDAO();
    AlbumSongDAO albumSongDAO = new AlbumSongDAO();
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody AlbumEntity entity){
       albumDAO.save(entity);
       return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/Put/{id}",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> put(@RequestBody AlbumEntity entity,@PathVariable("id") long id){
        if(id==entity.getId())
       albumDAO.save(entity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/GetDetail/{id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        AlbumEntity albumEntity =  albumDAO.getByID(id);
        if(albumEntity!=null)
            return new ResponseEntity<>(getAlbumDTO(albumEntity),HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/Delete",
            method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody AlbumEntity entity){
        albumDAO.delete(entity.getId());
        List<AlbumCategorymusicEntity> albumCategoryMusicEntity = albumCategoryMusicDAO.getId("albumid",entity.getId()+"");
        for(AlbumCategorymusicEntity item : albumCategoryMusicEntity){
            albumCategoryMusicDAO.delete(item.getId());
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
    albumCategoryMusicDAO.save(albumCategorymusicEntity);
    return new ResponseEntity<>("Update Completed",HttpStatus.OK);
}
    @RequestMapping(value ="/DeleteToCategory/{idCategoryMusic}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?>updateToCategoryMusic(@PathVariable("idCategoryMusic") Long id){
        albumCategoryMusicDAO.delete(id);
        return new ResponseEntity<>("Update Completed",HttpStatus.OK);
    }
    @RequestMapping(value ="/Count",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> count (){
        return new ResponseEntity<>(albumDAO.count(),HttpStatus.OK);
    }
    @RequestMapping(value ="/GetAllHasPage{itemOnpage}/{page}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> getPage (@PathVariable("page") int page,@PathVariable("itemOnpage") int itemOnpage){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(AlbumEntity.class);
        criteria.setItemPerPage(itemOnpage);
        criteria.setCurrentPage(page);
        List<AlbumEntity> albumEntityList = albumDAO.loadDataPagination(criteria);
        List<AlbumDTO> albumDTOList = new ArrayList<>();
            for ( AlbumEntity a : albumEntityList
                 ) {
                albumDTOList.add(getAlbumDTO(a));
            }
        return new ResponseEntity<>(albumDTOList,HttpStatus.OK);
        } catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value ="/GetTop{item}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public  ResponseEntity<?> getTop (@PathVariable("item") int item){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(AlbumEntity.class);
        criteria.setTop(item);
        List<AlbumEntity> albumEntityList = albumDAO.getTop10(criteria);
        if(!albumEntityList.isEmpty()){
            List<AlbumDTO> albumDTOList = new ArrayList<>();
            for (AlbumEntity ite: albumEntityList
                 ) {
                if(ite!=null)
                albumDTOList.add(getAlbumDTO(ite));
            }
            return new ResponseEntity<>(albumDTOList,HttpStatus.OK);
            }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    private AlbumDTO getAlbumDTO(AlbumEntity albumEntity){
        List<SongEntity> songEntityList = new ArrayList<>();
        List<AlbumSongEntity> albumSongEntityList = albumSongDAO.getId("albumid",albumEntity.getId()+"");
        if(!albumSongEntityList.isEmpty()){
            for ( AlbumSongEntity item : albumSongEntityList
                 ) {
                SongEntity songEntity = songDAO.getByID(item.getSongid());
                if(songEntity!=null)
                    songEntityList.add(songEntity);
            }
        }
        List<SingerEntity> singerEntityList = new ArrayList<>();
        List<AlbumSingerEntity> albumSingerEntityList = albumSingerDAO.getId("albumid",albumEntity.getId()+"");
        for(AlbumSingerEntity item : albumSingerEntityList){
            SingerEntity singerEntity = singerDAO.getByID(item.getSingerid());
            if(singerEntity!=null)
                singerEntityList.add(singerEntity);
        }
        AlbumDTO albumDTO = new AlbumDTO(albumEntity,songEntityList,singerEntityList);
        return albumDTO;
    }
}
