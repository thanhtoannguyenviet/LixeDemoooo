package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
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
    AuthorDAO authorDAO;
    ImageDAO imageDAO;
    AlbumDAO albumDAO;
    AlbumCategoryMusicDAO albumCategoryMusicDAO;
    CategorySongDAO categoryDAO;
    UploadDAO uploadDAO;
    SongSingerDAO songSingerDAO;
    SingerDAO singerDAO;
    SongCategorySongDAO songCategorySongDAO;
    CommentDAO commentDAO;
    @RequestMapping(value = "/Post",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> post(@RequestBody SongDTO entity){
        AuthorEntity authorEntity= entity.getAuthorEntity();
        authorDAO.Save(authorEntity);
        entity.getSongEntity().setAuthorid(authorDAO.GetId("name",entity.getAuthorEntity().getName()));
        imageDAO.Save(entity.getImageEntity());
        entity.getSongEntity().setImg(entity.getSongEntity().getImg()+","+imageDAO.GetId(entity.getImageEntity().getModel(),entity.getImageEntity().getEntryid()));
        albumDAO.Save(entity.getAlbumEntity());
        for ( UploadEntity upload : entity.getUploadEntityList()) {
            uploadDAO.Save(upload);
        }
        for (SingerEntity singer: entity.getSingerEntityList()) {
            SongSingerEntity ss = new SongSingerEntity();
            ss.setSingerid(singer.getId());
            ss.setSongid(entity.getSongEntity().getId());
            songSingerDAO.Save(ss);
        }
        for(CategorySongEntity cateSong : entity.getCategorySongEntityList()){
            SongCategorySongEntity sCS = new SongCategorySongEntity();
            sCS.setSongid(entity.getSongEntity().getId());
            sCS.setCategoryid(cateSong.getId());
            songCategorySongDAO.Save(sCS);
        }
        songDAO.Save(entity.getSongEntity());
        Long idSong = songDAO.GetId("songname",entity.getSongEntity().getSongName());
        AlbumEntity albumEntity = entity.getAlbumEntity();
        albumEntity.setListsongid(entity.getAlbumEntity().getListsongid()+","+idSong);
        albumDAO.Save(albumEntity);
        return new ResponseEntity<>("Post completed", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<?> Get (@PathVariable Long id){
      SongEntity songEntity = songDAO.GetByID(id);
      AlbumEntity albumEntity = albumDAO.GetByID(songEntity.getAlbumid());
      AuthorEntity authorEntity = authorDAO.GetByID(songEntity.getAuthorid());
      String[] array = songEntity.getImg().split(",");
      ImageEntity imageEntity = new ImageEntity();
      for (String item : array){
        if(item!=null) {
            imageEntity = imageDAO.GetByID(Long.parseLong(item));
        }
      }
      List<SongSingerEntity> numberSinger = songSingerDAO.GetId("songid",""+songEntity.getId());
      List<SingerEntity> lsSinger = new ArrayList<>();
        for ( SongSingerEntity item: numberSinger) {
            lsSinger.add(singerDAO.GetByID(item.getSingerid()));
        }
        List<UploadEntity> uploadEntities = new ArrayList<>();
        String[] arrayUpload = songEntity.getUploadsource().split(",");
        for(String item : arrayUpload){
            if(item!=null)
                uploadEntities.add(uploadDAO.GetByID(Long.parseLong(item)));
        }
        List<SongCategorySongEntity> lsSongCategory = songCategorySongDAO.GetId("songid",""+songEntity.getId());
        List<CategorySongEntity> categorySongEntities = new ArrayList<>();
        for(SongCategorySongEntity item : lsSongCategory){
            categorySongEntities.add(categoryDAO.GetByID(item.getCategoryid()));
        }
        List<CommentEntity> commentEntities = commentDAO.GetId("Song",songEntity.getId());
        SongDTO entity = new SongDTO(songEntity,albumEntity,authorEntity,imageEntity,lsSinger,uploadEntities,categorySongEntities,commentEntities);

        return new ResponseEntity<>(entity,HttpStatus.ACCEPTED);
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
            method = RequestMethod.PUT)
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
        else return new ResponseEntity<>("Not Found" + entity.getSongName(),HttpStatus.BAD_REQUEST);
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
}
