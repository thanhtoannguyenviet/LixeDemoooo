package Server.model.DAO;

import Server.model.DB.*;
import Server.model.DTO.SingerDTO;
import Server.model.DTO.SongDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class SongSiteDAO {
    SongDAO songDAO = new SongDAO();
    AlbumDAO albumDAO = new AlbumDAO();
    AuthorDAO authorDAO = new AuthorDAO();
    ImageDAO imageDAO = new ImageDAO();
    SingerDAO singerDAO = new SingerDAO();
    UploadDAO uploadDAO = new UploadDAO();
    AlbumSongDAO albumSongDAO = new AlbumSongDAO();
    SongSingerDAO songSingerDAO = new SongSingerDAO();
    CategorySongDAO categorySongDAO = new CategorySongDAO();
    SongCategorySongDAO songCategorySongDAO = new SongCategorySongDAO();
    public SongDTO getSongDTOById(Long id){
        SongEntity songEntity = songDAO.getByID(id);
        List<AlbumSongEntity> albumSongEntity = albumSongDAO.getId("songid",songEntity.getId()+"");
        AlbumEntity albumEntity = new AlbumEntity();
        if(!albumSongEntity.isEmpty()){
            albumEntity = albumDAO.getByID(albumSongEntity.get(0).getId());
        }
        AuthorEntity authorEntity = authorDAO.getByID(songEntity.getAuthorid());
        List<ImageEntity> imageEntityList = imageDAO.getId("Song",songEntity.getId());
        ImageEntity imageEntity = new ImageEntity();
        if(!imageEntityList.isEmpty()){
            imageEntity= imageEntityList.get(0);
        }
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("songid",songEntity.getId()+"");
        List<SingerEntity> singerEntityList = new ArrayList<>();
        for (SongSingerEntity item : songSingerEntityList) {
            SingerEntity singerEntity = singerDAO.getByID(item.getSingerid());
            if(singerEntity!=null)
                singerEntityList.add(singerEntity);
        }
        List<UploadEntity> uploadEntityList = uploadDAO.getId("Song",songEntity.getId());
        List<SongCategorysongEntity> songCategorysongEntityList = songCategorySongDAO.getId("songid",songEntity.getId()+"");
        List<CategorysongEntity> categorysongEntityList = new ArrayList<>();
        for(SongCategorysongEntity item : songCategorysongEntityList){
            CategorysongEntity categorysongEntity = categorySongDAO.getByID(item.getCategoryid());
            if(categorysongEntity!=null)
                categorysongEntityList.add(categorysongEntity);
        }
        return  new SongDTO(songEntity,albumEntity,authorEntity,imageEntity, Collections.unmodifiableList(singerEntityList),Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categorysongEntityList));
    }
    public SongDTO getSongDTOById(SongEntity songEntity){
        AlbumEntity albumEntity =null;
        List<AlbumSongEntity> albumSongEntityList = albumSongDAO.getId("songid",songEntity.getId()+"");
        if(!albumSongEntityList.isEmpty()){
            albumEntity = albumDAO.getByID(albumSongEntityList.get(0).getAlbumid());
        }
        AuthorEntity authorEntity = authorDAO.getByID(songEntity.getAuthorid());
        List<ImageEntity> imageEntityList = imageDAO.getId("Song",songEntity.getId());
        ImageEntity imageEntity = new ImageEntity();
        if(!imageEntityList.isEmpty()){
            imageEntity=imageEntityList.get(0);
        }
        else imageEntity = null;
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("songid",songEntity.getId()+"");
        List<SingerEntity> singerEntityList = new ArrayList<>();
        for (SongSingerEntity item : songSingerEntityList) {
            SingerEntity singerEntity = singerDAO.getByID(item.getSingerid());
            if(singerEntity!=null)
                singerEntityList.add(singerEntity);
        }
        List<UploadEntity> uploadEntityList = uploadDAO.getId("Song",songEntity.getId());
        List<SongCategorysongEntity> songCategorysongEntityList = songCategorySongDAO.getId("songid",songEntity.getId()+"");
        List<CategorysongEntity> categorysongEntityList = new ArrayList<>();
        for(SongCategorysongEntity item : songCategorysongEntityList){
            CategorysongEntity categorysongEntity = categorySongDAO.getByID(item.getCategoryid());
            if(categorysongEntity!=null)
                categorysongEntityList.add(categorysongEntity);
        }
        return  new SongDTO(songEntity,albumEntity,authorEntity,imageEntity, Collections.unmodifiableList(singerEntityList),Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categorysongEntityList));
    }
    public SingerDTO getSingerDTO(SingerEntity singerEntity) {
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("singerid", singerEntity.getId() + "");
        List<SongDTO> songDTOList = new ArrayList<>();
        for (SongSingerEntity item : songSingerEntityList
        ) {
            SongSiteDAO songSiteDAO = new SongSiteDAO();
            SongDTO songDTO = songSiteDAO.getSongDTOById(item.getSongid());
            if (songDTO != null)
                songDTOList.add(songDTO);
        }
        SingerDTO singerDTO = new SingerDTO(singerEntity, songDTOList);
        return singerDTO;
    }
}
