package Server.controller;

import Server.model.DAO.*;
import Server.model.DB.*;
import Server.model.DTO.SongDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
