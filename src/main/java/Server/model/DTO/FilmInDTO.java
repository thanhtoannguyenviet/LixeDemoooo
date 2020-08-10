package Server.model.DTO;

import Server.model.DB.FilmEntity;

public class FilmInDTO {
    private String apiToken = null;
    private FilmEntity filmEntity = new FilmEntity();

    public FilmInDTO() {
    }

    public FilmInDTO(String apiToken, FilmEntity filmEntity) {
        this.apiToken = apiToken;
        this.filmEntity = filmEntity;
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
}
