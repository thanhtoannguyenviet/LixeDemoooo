package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SongDTO {
    private SongEntity songEntity = new SongEntity();
    private AuthorEntity authorEntity = new AuthorEntity();
    private ImageEntity imageEntity = new ImageEntity();
    private List<SingerEntity> singerEntityList = new ArrayList<>();
    private List<UploadEntity> uploadEntityList = new ArrayList<>();
    private List<CategorySongEntity> categorySongEntityList = new ArrayList<>();
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    private Criteria criteria = new Criteria();

    public SongDTO() {
    }

    public SongDTO(SongEntity songEntity, AuthorEntity authorEntity, ImageEntity imageEntity, List<SingerEntity> singerEntityList, List<UploadEntity> uploadEntityList, List<CategorySongEntity> categorySongEntityList, List<CommentEntity> commentEntityList, Criteria criteria) {
        this.songEntity = songEntity;
        this.authorEntity = authorEntity;
        this.imageEntity = imageEntity;
        this.singerEntityList = singerEntityList;
        this.uploadEntityList = uploadEntityList;
        this.categorySongEntityList = categorySongEntityList;
        this.commentEntityList = commentEntityList;
        this.criteria = criteria;
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

    public List<CategorySongEntity> getCategorySongEntityList() {
        return categorySongEntityList;
    }

    public void setCategorySongEntityList(List<CategorySongEntity> categorySongEntityList) {
        this.categorySongEntityList = categorySongEntityList;
    }

    public List<CommentEntity> getCommentEntityList() {
        return commentEntityList;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
