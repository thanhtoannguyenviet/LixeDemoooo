package Server.model.DTO;

import Server.model.DB.SerifilmFilmEntity;

public class SerifilmFilmInDTO {
    private String apiToken = null;
    private SerifilmFilmEntity serifilmFilmEntity = new SerifilmFilmEntity();

    public SerifilmFilmInDTO() {
    }

    public SerifilmFilmInDTO(String apiToken, SerifilmFilmEntity serifilmFilmEntity) {
        this.apiToken = apiToken;
        this.serifilmFilmEntity = serifilmFilmEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SerifilmFilmEntity getSerifilmFilmEntity() {
        return serifilmFilmEntity;
    }

    public void setSerifilmFilmEntity(SerifilmFilmEntity serifilmFilmEntity) {
        this.serifilmFilmEntity = serifilmFilmEntity;
    }
}
