package Server.model.DTO;

import Server.model.DB.FilmCategoryfilmEntity;

public class FilmCategoryfilmInDTO {
    private String apiToken = null;
    private FilmCategoryfilmEntity filmCategoryfilmEntity = new FilmCategoryfilmEntity();

    public FilmCategoryfilmInDTO() {
    }

    public FilmCategoryfilmInDTO(String apiToken, FilmCategoryfilmEntity filmCategoryfilmEntity) {
        this.apiToken = apiToken;
        this.filmCategoryfilmEntity = filmCategoryfilmEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public FilmCategoryfilmEntity getFilmCategoryfilmEntity() {
        return filmCategoryfilmEntity;
    }

    public void setFilmCategoryfilmEntity(FilmCategoryfilmEntity filmCategoryfilmEntity) {
        this.filmCategoryfilmEntity = filmCategoryfilmEntity;
    }
}
