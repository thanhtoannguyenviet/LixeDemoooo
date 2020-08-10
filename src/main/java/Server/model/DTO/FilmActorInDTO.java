package Server.model.DTO;

import Server.model.DB.FilmActorEntity;

public class FilmActorInDTO {
    private String apiToken = null;
    private FilmActorEntity filmActorEntity = new FilmActorEntity();

    public FilmActorInDTO() {
    }

    public FilmActorInDTO(String apiToken, FilmActorEntity filmActorEntity) {
        this.apiToken = apiToken;
        this.filmActorEntity = filmActorEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public FilmActorEntity getFilmActorEntity() {
        return filmActorEntity;
    }

    public void setFilmActorEntity(FilmActorEntity filmActorEntity) {
        this.filmActorEntity = filmActorEntity;
    }
}
