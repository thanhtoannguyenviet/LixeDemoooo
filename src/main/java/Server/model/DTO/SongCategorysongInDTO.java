package Server.model.DTO;

import Server.model.DB.SongCategorysongEntity;

public class SongCategorysongInDTO {
    private String apiToken = null;
    private SongCategorysongEntity songCategorysongEntity = new SongCategorysongEntity();

    public SongCategorysongInDTO() {
    }

    public SongCategorysongInDTO(String apiToken, SongCategorysongEntity songCategorysongEntity) {
        this.apiToken = apiToken;
        this.songCategorysongEntity = songCategorysongEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public SongCategorysongEntity getSongCategorysongEntity() {
        return songCategorysongEntity;
    }

    public void setSongCategorysongEntity(SongCategorysongEntity songCategorysongEntity) {
        this.songCategorysongEntity = songCategorysongEntity;
    }
}
