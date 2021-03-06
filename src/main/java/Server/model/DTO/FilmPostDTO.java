package Server.model.DTO;

import Server.model.DB.FilmEntity;

public class FilmPostDTO {
    String apiToken;
    FilmEntity filmEntity = new FilmEntity();
    String keyword;

    public FilmPostDTO() {
    }

    public FilmPostDTO(String apiToken, FilmEntity filmEntity, String keyword) {
        this.apiToken = apiToken;
        this.filmEntity = filmEntity;
        this.keyword = keyword;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

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
