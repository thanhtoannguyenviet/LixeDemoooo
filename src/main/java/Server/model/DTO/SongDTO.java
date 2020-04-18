package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SongDTO {
    private SongEntity songEntity = new SongEntity();
    private AlbumEntity albumEntity = new AlbumEntity();
    private AuthorEntity authorEntity = new AuthorEntity();
    private ImageEntity imageEntity = new ImageEntity();
    private List<SingerEntity> singerEntityList = new ArrayList<>();
    private List<UploadEntity> uploadEntityList = new ArrayList<>();
    private List<CategorysongEntity> categorySongEntityList = new ArrayList<>();

    public SongDTO() {
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
