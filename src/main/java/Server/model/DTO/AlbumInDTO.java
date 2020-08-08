package Server.model.DTO;

import Server.model.DB.AlbumEntity;

public class AlbumInDTO {
    private String apiToken = null;
    private AlbumEntity albumEntity = new AlbumEntity();

    public AlbumInDTO() {
    }

    public AlbumInDTO(String apiToken, AlbumEntity albumEntity) {
        this.apiToken = apiToken;
        this.albumEntity = albumEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
    }
}
