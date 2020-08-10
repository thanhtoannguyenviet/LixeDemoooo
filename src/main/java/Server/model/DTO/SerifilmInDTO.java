package Server.model.DTO;

import Server.model.DB.SerifilmEntity;

public class SerifilmInDTO {
    private String apiToken = null;
    private SerifilmEntity serifilmEntity = new SerifilmEntity();

    public SerifilmInDTO(String apiToken, SerifilmEntity serifilmEntity) {
        this.apiToken = apiToken;
        this.serifilmEntity = serifilmEntity;
    }

    public SerifilmInDTO() {
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SerifilmEntity getSerifilmEntity() {
        return serifilmEntity;
    }

    public void setSerifilmEntity(SerifilmEntity serifilmEntity) {
        this.serifilmEntity = serifilmEntity;
    }
}
