package Server.model.DTO;

import Server.model.DB.*;

import java.util.ArrayList;
import java.util.List;

public class SeriFilmDTO {
    private SerifilmEntity seriFilmEntity = new SerifilmEntity();
    private List<FilmDTO> filmDTOList = new ArrayList<>();
    private Criteria criteria = new Criteria();
    public SeriFilmDTO(){}

    public SeriFilmDTO(SerifilmEntity seriFilmEntity, List<FilmDTO> filmDTOList, Criteria criteria) {
        this.seriFilmEntity = seriFilmEntity;
        this.filmDTOList = filmDTOList;
        this.criteria = criteria;
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

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
