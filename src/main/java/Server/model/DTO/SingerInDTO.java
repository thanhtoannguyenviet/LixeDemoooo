package Server.model.DTO;

import Server.model.DB.SingerEntity;

public class SingerInDTO {
    private String apiToken = null;
    private SingerEntity singerEntity = new SingerEntity();

    public SingerInDTO() {
    }

    public SingerInDTO(String apiToken, SingerEntity singerEntity) {
        this.apiToken = apiToken;
        this.singerEntity = singerEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SingerEntity getSingerEntity() {
        return singerEntity;
    }

    public void setSingerEntity(SingerEntity singerEntity) {
        this.singerEntity = singerEntity;
    }
}
