package Server.model.DTO;

import Server.model.DAO.*;
import Server.model.DB.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongDTO {
    private SongEntity songEntity = new SongEntity();
    private AlbumEntity albumEntity = new AlbumEntity();
    private AuthorEntity authorEntity = new AuthorEntity();
    private ImageEntity imageEntity = new ImageEntity();
    private List<SingerEntity> singerEntityList = new ArrayList<>();
    private List<UploadEntity> uploadEntityList = new ArrayList<>();
    private List<CategorysongEntity> categorySongEntityList = new ArrayList<>();

    public SongDTO(SongEntity songEntity) {
        this.songEntity = songEntity;
    }

    public SongDTO(SongEntity songEntity, AlbumEntity albumEntity, AuthorEntity authorEntity, ImageEntity imageEntity, List<SingerEntity> singerEntityList, List<UploadEntity> uploadEntityList, List<CategorysongEntity> categorySongEntityList) {
        this.songEntity = songEntity;
        this.albumEntity = albumEntity;
        this.authorEntity = authorEntity;
        this.imageEntity = imageEntity;
        this.singerEntityList = singerEntityList;
        this.uploadEntityList = uploadEntityList;
        this.categorySongEntityList = categorySongEntityList;
    }


    public SongDTO(SongEntity songEntity, AlbumEntity albumEntity, AuthorEntity authorEntity, ImageEntity imageEntity, List<SingerEntity> singerEntityList, List<UploadEntity> uploadEntityList, List<CategorysongEntity> categorySongEntityList, List<CommentEntity> commentEntityList, Criteria criteria) {
        this.songEntity = songEntity;
        this.albumEntity = albumEntity;
        this.authorEntity = authorEntity;
        this.imageEntity = imageEntity;
        this.singerEntityList = singerEntityList;
        this.uploadEntityList = uploadEntityList;
        this.categorySongEntityList = categorySongEntityList;
    }
    public SongDTO getSongDTObyE(SongEntity songEntity){
        AlbumDAO albumDAO=new AlbumDAO();
        AuthorDAO authorDAO = new AuthorDAO();
        SingerDAO singerDAO = new SingerDAO();
        CategorySongDAO categorySongDAO = new CategorySongDAO();
        SongCategorySongDAO songCategorySongDAO = new SongCategorySongDAO();
        SongSingerDAO songSingerDAO = new SongSingerDAO();
        ImageDAO imageDAO = new ImageDAO();
        UploadDAO uploadDAO = new UploadDAO();
        AlbumSongDAO albumSongDAO = new AlbumSongDAO();
        AuthorEntity authorEntity = authorDAO.getByID(songEntity.getAuthorid());
        List<AlbumSongEntity> albumSongEntityList = albumSongDAO.getId("songid",songEntity.getId()+"");
        AlbumEntity albumEntity = albumDAO.getByID(albumSongEntityList.get(0).getId());
        List<SongSingerEntity> songSingerEntityList = songSingerDAO.getId("songid",songEntity.getId()+"");
        List<SingerEntity> singerEntityList = new ArrayList<>();
        for (SongSingerEntity item : songSingerEntityList) {
            singerEntityList.add(singerDAO.getByID(item.getSingerid()));
        }
        List<UploadEntity> uploadEntityList = uploadDAO.getId("Song",songEntity.getId());
        List<SongCategorysongEntity> songCategorysongEntityList = songCategorySongDAO.getId("songid",songEntity.getId()+"");
        List<CategorysongEntity> categorysongEntityList = new ArrayList<>();
        for(SongCategorysongEntity item: songCategorysongEntityList){
            categorysongEntityList.add(categorySongDAO.getByID(item.getCategoryid()));
        }
        SongDTO songDTO = new SongDTO(songEntity,albumEntity,authorEntity,new ImageEntity(), Collections.unmodifiableList(singerEntityList),Collections.unmodifiableList(uploadEntityList),Collections.unmodifiableList(categorysongEntityList));
        return songDTO;
    }
    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
    }

    public SongEntity getSongEntity() {
        return songEntity;
    }

    public void setSongEntity(SongEntity songEntity) {
        this.songEntity = songEntity;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public List<SingerEntity> getSingerEntityList() {
        return singerEntityList;
    }

    public void setSingerEntityList(List<SingerEntity> singerEntityList) {
        this.singerEntityList = singerEntityList;
    }

    public List<UploadEntity> getUploadEntityList() {
        return uploadEntityList;
    }

    public void setUploadEntityList(List<UploadEntity> uploadEntityList) {
        this.uploadEntityList = uploadEntityList;
    }

    public List<CategorysongEntity> getCategorySongEntityList() {
        return categorySongEntityList;
    }

    public void setCategorySongEntityList(List<CategorysongEntity> categorySongEntityList) {
        this.categorySongEntityList = categorySongEntityList;
    }

}
