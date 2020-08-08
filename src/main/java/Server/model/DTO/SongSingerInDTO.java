package Server.model.DTO;

import Server.model.DB.SongSingerEntity;

public class SongSingerInDTO {
    private String apiToken = null;
    private SongSingerEntity songSingerEntity = new SongSingerEntity();

    public SongSingerInDTO() {
    }

    public SongSingerInDTO(String apiToken, SongSingerEntity songSingerEntity) {
        this.apiToken = apiToken;
        this.songSingerEntity = songSingerEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SongSingerEntity getSongSingerEntity() {
        return songSingerEntity;
    }

    public void setSongSingerEntity(SongSingerEntity songSingerEntity) {
        this.songSingerEntity = songSingerEntity;
    }
}
