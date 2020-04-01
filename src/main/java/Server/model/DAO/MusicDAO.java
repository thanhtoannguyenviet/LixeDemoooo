package Server.model.DAO;

import Server.model.DB.*;
import Server.model.DTO.SongDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class MusicDAO {
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
    public SongDTO GetSongDTO(Long id){
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
        return entity;
    }
    public void post(SongEntity song){
        AuthorEntity authorEntity = authorDAO.GetByID(song.getAuthorid());
        if(authorEntity!=null){
            authorEntity.setListsongid(authorEntity.getListsongid()+","+song.getAuthorid());
            authorDAO.Save(authorEntity);
        }
        AlbumEntity albumEntity = albumDAO.GetByID(song.getAlbumid());
        if(albumEntity!=null){
            albumEntity.setListsongid(albumEntity.getListsongid()+","+song.getAlbumid());
            albumDAO.Save(albumEntity);
        }
    }
}
