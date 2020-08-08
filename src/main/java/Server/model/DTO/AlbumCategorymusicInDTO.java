package Server.model.DTO;

import Server.model.DB.AlbumCategorymusicEntity;

public class AlbumCategorymusicInDTO {
    private String apiToken = null;
    private AlbumCategorymusicEntity albumCategorymusicEntity = new AlbumCategorymusicEntity();

    public AlbumCategorymusicInDTO() {
    }

    public AlbumCategorymusicInDTO(String apiToken, AlbumCategorymusicEntity albumCategorymusicEntity) {
        this.apiToken = apiToken;
        this.albumCategorymusicEntity = albumCategorymusicEntity;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public AlbumCategorymusicEntity getAlbumCategorymusicEntity() {
        return albumCategorymusicEntity;
    }

    public void setAlbumCategorymusicEntity(AlbumCategorymusicEntity albumCategorymusicEntity) {
        this.albumCategorymusicEntity = albumCategorymusicEntity;
    }
}
