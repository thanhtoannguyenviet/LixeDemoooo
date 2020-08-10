package Server.model.DTO;

import Server.model.DB.SongEntity;

public class SongInDTO {
    private String apiToken = null;
    private SongEntity songEntity = new SongEntity();

    public SongInDTO() {
    }

    public SongInDTO(String apiToken, SongEntity songEntity) {
        this.apiToken = apiToken;
        this.songEntity = songEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SongEntity getSongEntity() {
        return songEntity;
    }

    public void setSongEntity(SongEntity songEntity) {
        this.songEntity = songEntity;
    }
}
