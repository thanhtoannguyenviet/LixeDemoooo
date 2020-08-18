package Server.model.DTO;

import Server.model.DB.ImageEntity;
import Server.model.DB.SerifilmEntity;

import java.util.ArrayList;
import java.util.List;

public class SeriFilmDTO {
    private SerifilmEntity seriFilmEntity = new SerifilmEntity();
    private List<FilmDTO> filmDTOList = new ArrayList<>();
    private ImageEntity imageEntity = new ImageEntity();

    public ImageEntity getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(ImageEntity imageEntity) {
        this.imageEntity = imageEntity;
    }

    public SeriFilmDTO() {
    }

    public SeriFilmDTO(SerifilmEntity seriFilmEntity, List<FilmDTO> filmDTOList, ImageEntity imageEntity) {
        this.seriFilmEntity = seriFilmEntity;
        this.filmDTOList = filmDTOList;
        this.imageEntity = imageEntity;
    }

    public SerifilmEntity getSeriFilmEntity() {
        return seriFilmEntity;
    }

    public void setSeriFilmEntity(SerifilmEntity seriFilmEntity) {
        this.seriFilmEntity = seriFilmEntity;
    }

    public List<FilmDTO> getFilmDTOList() {
        return filmDTOList;
    }

    public void setFilmDTOList(List<FilmDTO> filmDTOList) {
        this.filmDTOList = filmDTOList;
    }

}
