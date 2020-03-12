package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class FilmDTO {
    private FilmEntity filmEntity = new FilmEntity();
    private DirectorEntity directorEntity = new DirectorEntity();
    private List<ActorEntity> actorEntityList = new ArrayList<>();
    private List<ImageEntity> imageEntity = new ArrayList<>();
    private List<UploadEntity> uploadEntityList = new ArrayList<>();
    private List<CategoryFilmEntity> categoryFilmEntityList = new ArrayList<>();
    private SeriFilmEntity seriCategoryFilmEntity = new SeriFilmEntity();
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    private Criteria criteria = new Criteria();

    public FilmDTO() {
    }

    public FilmDTO(FilmEntity filmEntity, DirectorEntity directorEntity, List<ActorEntity> actorEntityList, List<ImageEntity> imageEntity, List<UploadEntity> uploadEntityList, List<CategoryFilmEntity> categoryFilmEntityList, SeriFilmEntity seriCategoryFilmEntity, List<CommentEntity> commentEntityList, Criteria criteria) {
        this.filmEntity = filmEntity;
        this.directorEntity = directorEntity;
        this.actorEntityList = actorEntityList;
        this.imageEntity = imageEntity;
        this.uploadEntityList = uploadEntityList;
        this.categoryFilmEntityList = categoryFilmEntityList;
        this.seriCategoryFilmEntity = seriCategoryFilmEntity;
        this.commentEntityList = commentEntityList;
        this.criteria = criteria;
    }

    public FilmEntity getFilmEntity() {
        return filmEntity;
    }

    public void setFilmEntity(FilmEntity filmEntity) {
        this.filmEntity = filmEntity;
    }

    public DirectorEntity getDirectorEntity() {
        return directorEntity;
    }

    public void setDirectorEntity(DirectorEntity directorEntity) {
        this.directorEntity = directorEntity;
    }

    public List<ActorEntity> getActorEntityList() {
        return actorEntityList;
    }

    public void setActorEntityList(List<ActorEntity> actorEntityList) {
        this.actorEntityList = actorEntityList;
    }

    public List<ImageEntity> getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(List<ImageEntity> imageEntity) {
        this.imageEntity = imageEntity;
    }

    public List<UploadEntity> getUploadEntityList() {
        return uploadEntityList;
    }

    public void setUploadEntityList(List<UploadEntity> uploadEntityList) {
        this.uploadEntityList = uploadEntityList;
    }

    public List<CategoryFilmEntity> getCategoryFilmEntityList() {
        return categoryFilmEntityList;
    }

    public void setCategoryFilmEntityList(List<CategoryFilmEntity> categoryFilmEntityList) {
        this.categoryFilmEntityList = categoryFilmEntityList;
    }

    public SeriFilmEntity getSeriCategoryFilmEntity() {
        return seriCategoryFilmEntity;
    }

    public void setSeriCategoryFilmEntity(SeriFilmEntity seriCategoryFilmEntity) {
        this.seriCategoryFilmEntity = seriCategoryFilmEntity;
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
