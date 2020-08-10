package Server.model.DTO;

import Server.model.DB.AlbumSingerEntity;

public class AlbumSingerInDTO {
    private String apiToken = null;
    private AlbumSingerEntity albumSingerEntity = new AlbumSingerEntity();

    public AlbumSingerInDTO() {
    }

    public AlbumSingerInDTO(String apiToken, AlbumSingerEntity albumSingerEntity) {
        this.apiToken = apiToken;
        this.albumSingerEntity = albumSingerEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AlbumSingerEntity getAlbumSingerEntity() {
        return albumSingerEntity;
    }

    public void setAlbumSingerEntity(AlbumSingerEntity albumSingerEntity) {
        this.albumSingerEntity = albumSingerEntity;
    }
}
