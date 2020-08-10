package Server.model.DTO;

import Server.model.DB.DirectorFilmEntity;

public class DirectorFilmInDTO {
    private String apiToken = null;
    private DirectorFilmEntity directorFilmEntity = new DirectorFilmEntity();

    public DirectorFilmInDTO() {
    }

    public DirectorFilmInDTO(String apiToken, DirectorFilmEntity directorFilmEntity) {
        this.apiToken = apiToken;
        this.directorFilmEntity = directorFilmEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public DirectorFilmEntity getDirectorFilmEntity() {
        return directorFilmEntity;
    }

    public void setDirectorFilmEntity(DirectorFilmEntity directorFilmEntity) {
        this.directorFilmEntity = directorFilmEntity;
    }
}
