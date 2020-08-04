package Server.model.DTO;

import Server.model.DB.FilmEntity;

public class FilmPostDTO {
    FilmEntity filmEntity = new FilmEntity();
    String keyword;

    public FilmPostDTO(FilmEntity filmEntity, String keyword) {
        this.filmEntity = filmEntity;
        this.keyword = keyword;
    }
    public FilmPostDTO(){}
    public FilmEntity getFilmEntity() {
        return filmEntity;
    }

    public void setFilmEntity(FilmEntity filmEntity) {
        this.filmEntity = filmEntity;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
