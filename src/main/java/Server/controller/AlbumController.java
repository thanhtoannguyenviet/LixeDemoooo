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
    SongDAO songDAO = new SongDAO();
    AlbumCategoryMusicDAO albumCategoryMusicDAO = new AlbumCategoryMusicDAO();
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    SingerDAO singerDAO = new SingerDAO();
    AlbumSingerDAO albumSingerDAO = new AlbumSingerDAO();
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
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        AlbumEntity albumEntity =  albumDAO.getByID(id);
        return new ResponseEntity<>(getAlbumDTO(albumEntity),HttpStatus.OK);
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
    @RequestMapping(value ="/Count", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> count (){
        return new ResponseEntity<>(albumDAO.count(),HttpStatus.OK);
    }
    @RequestMapping(value ="/GetAllHasPage{itemOnpage}/{page}", method = RequestMethod.GET)
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
    @RequestMapping(value ="/GetTop{item}", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> getTop (@PathVariable("item") int item){
        try{
        Criteria criteria = new Criteria();
        criteria.setClazz(AlbumEntity.class);
        criteria.setTop(item);
        return new ResponseEntity<>(albumDAO.getTop10(criteria),HttpStatus.OK);
        }
        catch (Exception e) {
            LogEntity log = new LogEntity(e);
            (new LogDAO()).save(log);
            e.printStackTrace();
            return new ResponseEntity<>("If you are admin, Check table Log to see ErrorMsg",HttpStatus.BAD_REQUEST);
        }
    }
    private AlbumDTO getAlbumDTO(AlbumEntity albumEntity){
//        String[] lsSong = albumEntity.getListsongid().split(",");
        List<SongEntity> songEntityList = new ArrayList<>();
//        for ( String item : lsSong) {
//            if(!item.isEmpty()&&!item.isBlank()){
//                songEntityList.add(songDAO.getByID(Long.parseLong(item)));
//            }
//        }
        List<SingerEntity> singerEntityList = new ArrayList<>();
        List<AlbumSingerEntity> albumSingerEntityList = albumSingerDAO.getId("albumid",albumEntity.getId()+"");
        for(AlbumSingerEntity item : albumSingerEntityList){
            singerEntityList.add(singerDAO.getByID(item.getSingerid()));
        }
        AlbumDTO albumDTO = new AlbumDTO(albumEntity,songEntityList,singerEntityList);
        return albumDTO;
    }
}
