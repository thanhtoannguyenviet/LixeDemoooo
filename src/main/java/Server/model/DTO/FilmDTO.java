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
    private List<CategoryfilmEntity> categoryFilmEntityList = new ArrayList<>();
    private SerifilmEntity seriCategoryFilmEntity = new SerifilmEntity();

    public FilmDTO() {
    }

    public FilmDTO(FilmEntity filmEntity, DirectorEntity directorEntity, List<ActorEntity> actorEntityList, List<ImageEntity> imageEntity, List<UploadEntity> uploadEntityList, List<CategoryfilmEntity> categoryFilmEntityList, SerifilmEntity seriCategoryFilmEntity) {
        this.filmEntity = filmEntity;
        this.directorEntity = directorEntity;
        this.actorEntityList = actorEntityList;
        this.imageEntity = imageEntity;
        this.uploadEntityList = uploadEntityList;
        this.categoryFilmEntityList = categoryFilmEntityList;
        this.seriCategoryFilmEntity = seriCategoryFilmEntity;
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

    public List<CategoryfilmEntity> getCategoryFilmEntityList() {
        return categoryFilmEntityList;
    }

    public void setCategoryFilmEntityList(List<CategoryfilmEntity> categoryFilmEntityList) {
        this.categoryFilmEntityList = categoryFilmEntityList;
    }

    public SerifilmEntity getSeriCategoryFilmEntity() {
        return seriCategoryFilmEntity;
    }

    public void setSeriCategoryFilmEntity(SerifilmEntity seriCategoryFilmEntity) {
        this.seriCategoryFilmEntity = seriCategoryFilmEntity;
    }

}
